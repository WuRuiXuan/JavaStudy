package example.study.decorator;

/**
 * 装饰者抽象类
 */

public class Decorator implements Drink {
	
	private Drink drink;
	
	public Decorator(Drink drink) {
		this.drink = drink;
	}

	@Override
	public String description() {
		return drink.description();
	}

	@Override
	public float cost() {
		return drink.cost();
	}

}
