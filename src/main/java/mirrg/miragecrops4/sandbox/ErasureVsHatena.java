package mirrg.miragecrops4.sandbox;

import java.util.List;

public class ErasureVsHatena
{

	static class Tuple<A, B>
	{
		A a;
		B b;
	}

	static class HashEntries<A extends CharSequence, B extends Number>
	{
		List<Tuple<A, B>> entries;
	}

	static class C
	{
		@SuppressWarnings("rawtypes")
		static void a(HashEntries he)
		{
			//Tuple entry = he.entries.get(0);
			// he.entriesの段階でList型、ListからgetするとObjectが帰る
		}

		static void b(HashEntries<?, ?> he)
		{
			Tuple<? extends CharSequence, ? extends Number> entry2 = he.entries.get(0);
			// 表記上は同じ?にまとめられているが、内部では区別されているので厳密な型を取得できる
		}
	}

}
