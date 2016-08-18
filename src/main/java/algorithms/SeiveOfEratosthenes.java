package algorithms;

public class SeiveOfEratosthenes {

	public static void main(String[] args) {
		
		boolean a[] = new boolean[100];
		for(int i =0; i<a.length; i++) {
			a[i] = true;
		}
		
		a[0] = false;
		a[1] = false;
		
		for(int i=2; i<a.length; i++) {
			if(a[i] == true) {
				for(int p = i*2; p<a.length; p+=i){
					a[p] = false;
				}
			}
		}
		
		for(int i =0; i< a.length; i++) {
			if(a[i] == true) {
				System.out.println(i);
			}
		}
		
	}

}
