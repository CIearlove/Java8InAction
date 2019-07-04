package Chapter01;

import java.io.File;
import java.io.FileFilter;

public class TestCase {

	/**
	 * 你想要筛选一个目录中的所有隐藏文件。
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * 接口是不可以实例化的。
		 * new FileFilter(){}是匿名内部类的写法。
		 * 它实例化了一个匿名内部类，而这个匿名内部类实现了FileFilter接口。
		 */
		/*
		File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
			public boolean accept(File file){
				return file.isHidden();
			}
		});
		*/
		
		/*
		 * java8里，你可以把代码重写成这个样子：
		 */
		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
	}

}
