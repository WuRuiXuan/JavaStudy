package example.study.decorator;

/**
 * 装饰者模式
 */

public class DecoratorDemo {

	public static void main(String[] args) {
		Drink drink = new SoybeanMilk();
		SugarDecorator sugar = new SugarDecorator(drink);
		BlackBeanDecorator blackBean = new BlackBeanDecorator(sugar);
		EggDecorator egg = new EggDecorator(blackBean);
		
		System.out.println("早餐是：" + egg.description());
		System.out.println("共花了：" + egg.cost() + "块钱");
	}
	
}
