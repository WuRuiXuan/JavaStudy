package example.study.decorator;

/**
 * 具体的装饰者类
 */

public class SoybeanMilk implements Drink {

	@Override
	public String description() {
		return "纯豆浆";
	}

	@Override
	public float cost() {
		return 3f;
	}
	
}
