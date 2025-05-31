package helloWorld;

public class HelloWorld {

	public static void main(String [] args) {
		
		if(args.length ==0)
			System.out.println("Use: java helloWorld.HelloWorld \"Larielly\" ");
		
		System.out.println("Olá" + args[0]);
		
	}
	
}
