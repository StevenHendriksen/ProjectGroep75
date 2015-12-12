package ss.week4;



public class Product implements Function{
	private Function functionf;
	private Function functiong;
	private Product product1;
	private Product product2;
	private Sum sum;
	
	public Product(Function f, Function g){
		functionf = f;
		functiong = g;
	}
	
	public double apply(int apply){
		return functionf.apply(apply) * functiong.apply(apply);
	}
	
	public Function derivative(){
		product1 = new Product(functionf.derivative(), functiong);
		product2 = new Product(functiong.derivative(), functionf);
		sum = new Sum(product1, product2);
		return sum;
	}
	
	public Function integrand(){
		return null;
	}
	public String print(){
		return functionf.print() + " * " + functiong.print();
	}
}
