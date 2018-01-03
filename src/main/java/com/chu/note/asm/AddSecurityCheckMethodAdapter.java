package com.chu.note.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 继承自 MethodAdapter的 AddSecurityCheckMethodAdapter，在“operation”方法首部插入对
 * SecurityChecker.checkSecurity()的调用
 * 
 * @author zhurongzeng
 *
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {

	public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	/**
	 * ClassReader读到每个方法的首部时调用 visitCode()，在这个重写方法里，我们用
	 * visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker","checkSecurity", "()V");
	 * 插入了安全检查功能。
	 */
	@Override
	public void visitCode() {
		visitMethodInsn(Opcodes.INVOKESTATIC, "com/chu/note/asm/SecurityChecker", "checkSecurity", "()V");
	}
}
