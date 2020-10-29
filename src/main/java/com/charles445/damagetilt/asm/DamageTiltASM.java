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
		if(transformedName.equals("net.minecraft.entity.EntityLivingBase"))
			return transformEntityLivingBase(basicClass);
		
		return basicClass;
	}
	
	public byte[] transformEntityLivingBase(byte[] basicClass)
	{
		ClassNode classNode = ASMHelper.readClassFromBytes(basicClass);
		
		for(MethodNode mNode : classNode.methods)
		{
			if(mNode.name.equals("func_70653_a") || mNode.name.equals("knockBack"))
			{
				System.out.println("DamageTilt Found matching method "+mNode.name);
				if(mNode.desc.equals("(Lnet/minecraft/entity/Entity;FDD)V"))
				{
					System.out.println("DamageTilt Matching method has matching desc");
					
					InsnList list = new InsnList();
					list.add(new VarInsnNode(Opcodes.ALOAD, 0)); //Push this to stack
					list.add(new MethodInsnNode(Opcodes.INVOKESTATIC,"com/charles445/damagetilt/EventHandler","onKnockback","(Lnet/minecraft/entity/EntityLivingBase;)V", false));
					mNode.instructions.insertBefore(ASMHelper.findFirstInstruction(mNode), list);
					
					System.out.println("DamageTilt Patched method "+mNode.name);
					return ASMHelper.writeClassToBytes(classNode, ClassWriter.COMPUTE_MAXS);
				}
			}
		}
		return basicClass;
	}
}
