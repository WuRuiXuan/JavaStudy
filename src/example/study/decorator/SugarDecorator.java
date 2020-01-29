package example.study.decorator;

public class SugarDecorator extends Decorator {

	public SugarDecorator(Drink drink) {
		super(drink);
	}
	
	@Override
	public String description() {
		return super.description() + "+糖";
	}
	
	@Override
	public float cost() {
		return super.cost() + 0.5f;
	}
}
