package com.charles445.damagetilt.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.charles445.damagetilt.asm.helper.ASMHelper;

import net.minecraft.launchwrapper.IClassTransformer;

public class DamageTiltASM implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if(transformedName.equals("net.minecraft.entity.Entity"))
		{
			return transformEntity(basicClass);
		}
		
		return basicClass;
	}
	
	public byte[] transformEntity(byte[] basicClass)
	{
		ClassNode cNode = ASMHelper.readClassFromBytes(basicClass);
		
		//net.minecraft.entity.Entity
		
		for(MethodNode mNode : cNode.methods)
		{
			if(mNode.name.equals("func_70016_h") || mNode.name.equals("setVelocity"))
			{
				//Matching name, verify correct desc
				if(mNode.desc.equals("(DDD)V"))
				{
					//Found a match
					InsnList hook = new InsnList();
					
					hook.add(new VarInsnNode(Opcodes.ALOAD,0)); //push this to stack
					hook.add(new VarInsnNode(Opcodes.DLOAD,1)); //push x to stack (increment 2 for doubles as param)
					hook.add(new VarInsnNode(Opcodes.DLOAD,3)); //push y to stack
					hook.add(new VarInsnNode(Opcodes.DLOAD,5)); //push z to stack
					hook.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "com/charles445/damagetilt/HookEntity", "setVelocity", "(Lnet/minecraft/entity/Entity;DDD)V", false));
					
					//Add to method head
					mNode.instructions.insertBefore(ASMHelper.findFirstInstruction(mNode), hook);
					
					System.out.println("Damage Tilt Patched net.minecraft.entity.Entity");
					return ASMHelper.writeClassToBytes(cNode, ClassWriter.COMPUTE_MAXS);
				}
					
			}
		}
		
		System.out.println("Damage Tilt Skipping patch for net.minecraft.entity.Entity");
		return basicClass;
	}
}
