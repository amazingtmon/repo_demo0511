package design.duck;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DuckSimulation {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("design\\duck\\duckBean.xml");
		
		/*
		 * ApplicationContext로 인해 관리받고 있다.
		 * */
		Duck myDuck = null;
		myDuck = (Duck) context.getBean("myDuck");
		myDuck.performFly();
		myDuck.performQuack();
		myDuck.display();
		System.out.println("============================");
		
		Duck yourDuck = null;
		yourDuck = (Duck) context.getBean("yourDuck");
		yourDuck.performFly();
		yourDuck.performQuack();
		yourDuck.display();
		System.out.println("============================");
		
		Duck testDuck = null;
		testDuck = (Duck) context.getBean("testDuck");
		testDuck.performFly();
		testDuck.performQuack();
		testDuck.display();
		System.out.println("============================");
		/*
		 * 관리받고 있지 않다.
		 * 스프링을 사용하고 있지 않는것.
		 * */
		myDuck = null;
		myDuck = new MalladDuck();
		myDuck.performFly();
		myDuck.display();
	}

}
