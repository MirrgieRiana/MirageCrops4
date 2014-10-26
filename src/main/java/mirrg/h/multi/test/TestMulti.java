package mirrg.h.multi.test;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import mirrg.h.multi.IMeta;
import mirrg.h.multi.IMulti;
import mirrg.h.multi.Meta;
import mirrg.h.multi.Multi;

import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TestMulti
{

	public static void main(String[] args)
	{
		JUnitCore.main(TestMulti.class.getName());
	}

	@Test
	@SuppressWarnings("unchecked")
	public <MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
		void test()
	{
		MULTI multi;

		multi = (MULTI) new Multi<>(new IMeta[5]);
		Assert.assertTrue("インスタンス生成", true);

		Assert.assertSame(multi, multi.getMulti());

		Assert.assertEquals(5, multi.getLength());

		Assert.assertFalse(multi.isBound(0));
		Assert.assertFalse(multi.isBound(4));

		try {
			multi.isBound(-1);
			Assert.fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		try {
			multi.isBound(5);
			Assert.fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			Assert.assertTrue(true);
		}

		// ■multiのbind

		// 0番地にmetaから連結
		META meta0;
		{
			meta0 = (META) new Meta<>();
			Assert.assertTrue("インスタンス生成", true);

			Assert.assertSame(meta0, meta0.getMeta());

			// ■metaのbind

			try {
				meta0.getIndex();
				Assert.fail();
			} catch (NullPointerException e) {
				Assert.assertTrue(true);
			}

			try {
				multi.getMeta(0);
				Assert.fail();
			} catch (NullPointerException e) {
				Assert.assertTrue(true);
			}

			Assert.assertSame(null, meta0.getMulti());
			meta0.bind(0, multi);
			Assert.assertEquals(0, meta0.getIndex());
			Assert.assertSame(multi, meta0.getMulti());
			Assert.assertSame(meta0, multi.getMeta(0));
		}

		// 番地を取得しようとする
		test_getMeta("[0][0][0][0][0]", multi, meta0);

		// 1番地にmultiから連結
		META meta1;
		{
			meta1 = (META) new Meta<>();
			Assert.assertTrue("インスタンス生成", true);

			Assert.assertSame(meta1, meta1.getMeta());

			meta1.bind(1, multi);
		}

		// 番地を取得
		test_getMeta("[0][1][0][0][0]", multi, meta0, meta1);

		// 0番地をmultiから連結解除
		{
			META currentMeta = meta0;
			int currentIndex = 0;
			Assert.assertTrue(currentMeta.isBound());
			Assert.assertTrue(multi.isBound(currentIndex));
			Assert.assertEquals(currentIndex, currentMeta.getIndex());
			Assert.assertTrue(multi.clearBindind(currentIndex));
			Assert.assertFalse(currentMeta.isBound());
			Assert.assertFalse(multi.isBound(currentIndex));
			try {
				currentMeta.getIndex();
				Assert.fail();
			} catch (NullPointerException e) {
				Assert.assertTrue(true);
			}
		}

		// 番地を取得
		test_getMeta("E[0]EEE", multi, meta1);

		// 1番地をmetaから連結解除
		{
			META currentMeta = meta1;
			int currentIndex = 1;
			Assert.assertTrue(currentMeta.isBound());
			Assert.assertTrue(multi.isBound(currentIndex));
			Assert.assertEquals(currentIndex, meta1.getIndex());
			Assert.assertTrue(currentMeta.clearBindind());
			Assert.assertFalse(currentMeta.isBound());
			Assert.assertFalse(multi.isBound(currentIndex));
			try {
				currentMeta.getIndex();
				Assert.fail();
			} catch (NullPointerException e) {
				Assert.assertTrue(true);
			}
		}

		// 番地を取得
		test_getMeta("EEEEE", multi);

		// 2番地をmultiから連結解除するが何も起こらない
		{
			int currentIndex = 2;
			Assert.assertFalse(multi.isBound(currentIndex));
			Assert.assertFalse(multi.clearBindind(currentIndex));
			Assert.assertFalse(multi.isBound(currentIndex));
		}

		// 番地を取得
		test_getMeta("EEEEE", multi);

		// ■イテレート

		// 確認
		Assert.assertFalse(multi.isBound(0));
		Assert.assertFalse(multi.isBound(1));
		Assert.assertFalse(multi.isBound(2));
		Assert.assertFalse(multi.isBound(3));
		Assert.assertFalse(multi.isBound(4));

		{
			StringBuffer sb = new StringBuffer("aaaaa");
			for (META meta : multi) {
				sb.setCharAt(meta.getIndex(), 'b');
			}
			Assert.assertEquals("aaaaa", sb.toString());
		}

		// 3に連結
		META meta3 = (META) new Meta<>();
		multi.bind(3, meta3);

		{
			StringBuffer sb = new StringBuffer("aaaaa");
			for (META meta : multi) {
				sb.setCharAt(meta.getIndex(), 'b');
			}
			Assert.assertEquals("aaaba", sb.toString());
		}

		// 0に連結
		multi.bind(0, meta0);

		{
			StringBuffer sb = new StringBuffer("aaaaa");
			for (META meta : multi) {
				sb.setCharAt(meta.getIndex(), 'b');
			}
			Assert.assertEquals("baaba", sb.toString());
		}

		// 4に連結
		META meta4 = (META) new Meta<>();
		multi.bind(4, meta4);

		{
			StringBuffer sb = new StringBuffer("aaaaa");
			for (META meta : multi) {
				sb.setCharAt(meta.getIndex(), 'b');
			}
			Assert.assertEquals("baabb", sb.toString());
		}

		// 1, 2に連結
		META meta2 = (META) new Meta<>();
		multi.bind(1, meta1);
		multi.bind(2, meta2);

		{
			StringBuffer sb = new StringBuffer("aaaaa");
			for (META meta : multi) {
				sb.setCharAt(meta.getIndex(), 'b');
			}
			Assert.assertEquals("bbbbb", sb.toString());
		}

	}

	/**
	 * E：例外、N：null、[number]：メタ
	 */
	@SuppressWarnings("unused")
	private static <MULTI extends IMulti<MULTI, META>, META extends IMeta<MULTI, META>>
		void test_getMeta(String signature, MULTI multi, Object... metas)
	{
		List<Object> list = Arrays.asList(metas);
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < multi.getLength(); i++) {

			META meta;
			try {
				meta = multi.getMeta(i);
			} catch (NullPointerException e) {
				sb.append('E');
				continue;
			}

			if (meta == null) {
				sb.append('N');
				continue;
			}

			sb.append('[');
			sb.append(list.indexOf(meta));
			sb.append(']');

		}

		Assert.assertEquals(signature, sb.toString());
	}
}
