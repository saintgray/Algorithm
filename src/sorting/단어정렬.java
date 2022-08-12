package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//	문제
//	알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
//	
//	길이가 짧은 것부터
//	길이가 같으면 사전 순으로
//	입력
//	첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000) 
//	둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 
//	주어지는 문자열의 길이는 50을 넘지 않는다.
//	
//	출력
//	조건에 따라 정렬하여 단어들을 출력한다.
//	단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.

public class 단어정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int words = Integer.parseInt(in.readLine());
		String[] wordsArr = new String[words];
		int filled=0;
		while(words!=0) {
			String input = in.readLine();
			
			for (int i = 0; i < wordsArr.length; i++) {
				if(wordsArr[i] == null) {
					wordsArr[i] = input;
					filled++;
					break;
				}else if (wordsArr[i].length() > input.length()) {
					for(int j=filled-1; j>=i; j--) {
						wordsArr[j+1] = wordsArr[j];
					}
					wordsArr[i] = input;
					filled++;
					break;
				}else if(wordsArr[i].length() < input.length()) {
					// no action;
				}else {
					if(wordsArr[i].equals(input)) {
						break;
					}else {
						if(wordsArr[i].compareTo(input)>0) {
							for(int j=filled-1; j>=i; j--) {
								wordsArr[j+1] = wordsArr[j];
							}
							wordsArr[i] = input;
							filled++;
							break;
						}else if(wordsArr[i].compareTo(input)<0){
							// no action
						}
					}
				}
			}
			words--;
		}
		printArr(wordsArr);
		in.close();
		
	}
	
	
	
	private static void printArr(String[] arr) {
		for(String ele : arr) {
			if(ele==null) {
				break;
			}else {
				System.out.printf("%s\n",ele);
			}
		}
	}
}
