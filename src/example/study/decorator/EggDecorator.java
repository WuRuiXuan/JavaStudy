package example.study.decorator;

public class EggDecorator extends Decorator {

	public EggDecorator(Drink drink) {
		super(drink);
	}
	
	@Override
	public String description() {
		return super.description() + "+鸡蛋";
	}
	
	@Override
	public float cost() {
		return super.cost() + 3f;
	}
}
