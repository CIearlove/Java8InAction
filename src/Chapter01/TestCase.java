package Chapter01;

import java.io.File;
import java.io.FileFilter;

public class TestCase {

	/**
	 * ����Ҫɸѡһ��Ŀ¼�е����������ļ���
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * �ӿ��ǲ�����ʵ�����ġ�
		 * new FileFilter(){}�������ڲ����д����
		 * ��ʵ������һ�������ڲ��࣬����������ڲ���ʵ����FileFilter�ӿڡ�
		 */
		/*
		File[] hiddenFiles = new File(".").listFiles(new FileFilter(){
			public boolean accept(File file){
				return file.isHidden();
			}
		});
		*/
		
		/*
		 * java8�����԰Ѵ�����д��������ӣ�
		 */
		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
	}

}
