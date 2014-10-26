package mirrg.miragecrops4.sandbox;

/*
public class TestCast3
{

	// ライブラリ

	static class A1<A extends A1<A, B>, B extends B1<A, B>>
	{
		A a;
		B b;
	}

	static class B1<A extends A1<A, B>, B extends B1<A, B>>
	{
		A a;
		B b;
	}

	// コア

	static class A2<A extends A2<A, B>, B extends B2<A, B>> extends A1<A, B>
	{
		int h;
	}

	static class B2<A extends A2<A, B>, B extends B2<A, B>> extends B1<A, B>
	{
		int h;
	}

	static class B2_2<A extends A2<A, B>, B extends B2<A, B>> extends B2<A, B>
	{
	}

	// コアモジュール

	static class Module1
	{

		static interface IA2E
		{
			IA2E getA(); IB2E getB();
			void setA(IA2E a); void setB(IB2E b);
			int getH();
		}

		static interface IB2E
		{
			IA2E getA(); IB2E getB();
			void setA(IA2E a); void setB(IB2E b);
			int getH();
		}

		static class A2E extends A2<A2E, B2E> implements IA2E
		{
			@Override
			public IA2E getA() { return null; }
			@Override
			public IB2E getB() { return null; }
			@Override
			public void setA(IA2E a) { }
			@Override
			public void setB(IB2E b) { }
			@Override
			public int getH() { return 0; }
		}

		static class B2E extends B2<A2E, B2E> implements IB2E
		{
			@Override
			public IA2E getA() { return null; }
			@Override
			public IB2E getB() { return null; }
			@Override
			public void setA(IA2E a) { }
			@Override
			public void setB(IB2E b) { }
			@Override
			public int getH() { return 0; }
		}

		static class B2_2E extends B2E { }

		static IA2E a;
		static IB2E b;

		void init()
		{
			a = new A2E();
			b = new B2E();
			b = new B2_2E();
		}

		void configure()
		{
			a.setA(a);
			b.setA(a);
			a.setB(b);
			b.setB(b);

			b.setB(new B2_2E());
		}
	}

}
*/
