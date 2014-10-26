package mirrg.miragecrops4.sandbox;

/*
public class TestCast2
{

	// ライブラリ

	static interface IA1<A extends IA1<A, B>, B extends IB1<A, B>>
	{
		A getA();
		B getB();
		void setA(A a);
		void setB(B b);
	}

	static interface IB1<A extends IA1<A, B>, B extends IB1<A, B>>
	{
		A getA();
		B getB();
		void setA(A a);
		void setB(B b);
	}

	static class A1<A extends IA1<A, B>, B extends IB1<A, B>> implements IA1<A, B>
	{
		@Override
		public A getA() { return null; }
		@Override
		public B getB() { return null; }
		@Override
		public void setA(A a) { }
		@Override
		public void setB(B b) { }
	}

	static class B1<A extends IA1<A, B>, B extends IB1<A, B>> implements IB1<A, B>
	{
		@Override
		public A getA() { return null; }
		@Override
		public B getB() { return null; }
		@Override
		public void setA(A a) { }
		@Override
		public void setB(B b) { }
	}

	// コア

	static interface IA2<A extends IA1<A, B>, B extends IB1<A, B>> extends IA1<A, B>
	{ int getH(); }

	static interface IB2<A extends IA1<A, B>, B extends IB1<A, B>> extends IB1<A, B>
	{ int getH(); }

	static class A2<A extends IA2<A, B>, B extends IB2<A, B>> extends A1<A, B> implements IA2<A, B>
	{
		@Override
		public int getH() { return 0; }
	}

	static class B2<A extends IA2<A, B>, B extends IB2<A, B>> extends B1<A, B> implements IB2<A, B>
	{
		@Override
		public int getH() { return 0; }
	}

	static class B2_2<A extends IA2<A, B>, B extends IB2<A, B>> extends B2<A, B> implements IB2<A, B>
	{
	}

	// コアモジュール

	static class Module1
	{

		static interface IA2E extends IA2<IA2E, IB2E>
		{
		}

		static interface IB2E extends IB2<IA2E, IB2E>
		{
		}

		static class A2E extends B2_2<IA2E, IB2E> implements IA2E
		{
		}

		static class B2E extends B2_2<IA2E, IB2E> implements IB2E
		{
		}

		static class B2_2E extends B2_2<IA2E, IB2E> implements IB2E
		{
		}

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