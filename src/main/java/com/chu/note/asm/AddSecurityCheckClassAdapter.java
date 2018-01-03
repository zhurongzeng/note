package com.chu.note.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * ClassAdapter是 ASM 框架提供的一个默认类，负责沟通 ClassReader和 ClassWriter。
 * 如果想要改变ClassReader处读入的类，然后从 ClassWriter处输出，可以重写相应的 ClassAdapter函数。 这里，为了改变
 * Account类的operation 方法，我们将重写 visitMethdod方法。
 * 
 * @author zhurongzeng
 *
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {

	public AddSecurityCheckClassAdapter(ClassVisitor cv) {
		// Responsechain的下一个 ClassVisitor，这里我们将传入 ClassWriter，负责改写后代码的输出
		super(cv);
	}

	/**
	 * 重写 visitMethod，访问到 "operation" 方法时，给出自定义 MethodVisitor，实际改写方法内容
	 */
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
		if (mv != null && "operation".equals(name)) {
			mv = new AddSecurityCheckMethodAdapter(mv);
		}
		return mv;
	}

}
