package com.chu.note.asm;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * 集成上面定义的 ClassAdapter，ClassReader和 ClassWriter产生修改后的 Account类文件
 * 
 * @author zhurongzeng
 *
 */
public class Generator {
	public static void main(String[] args) throws Exception {
		ClassReader cr = new ClassReader("com/chu/note/asm/Account");
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
		cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
		byte[] data = cw.toByteArray();
		File file = new File("E:/workspace/note/bin/com/chu/note/asm/Account.class");
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(data);
		fo.close();
	}
}
