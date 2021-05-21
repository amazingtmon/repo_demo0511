package design.duck;
/*
 * 생성자는 올 수 없다.
 * 일반변수는 올 수 없다.
 * 메소드 이름 앞에 abstract는 생략 가능하다.
 * 메소드 뒤에 세미콜론 으로 끝난다.
 * */
public interface FlyBehavior {
	//public FlyBehavior() {}
	public void fly();
}
