package mirrg.miragecrops4.sandbox;

/*
 * public class TestCast { // ライブラリ static class A1<A extends A1<A, B>, B
 * extends B1<A, B>> { A a; B b; } static class B1<A extends A1<A, B>, B extends
 * B1<A, B>> { A a; B b; } // 実装 static class A2<A extends A2<A, B>, B extends
 * B2<A, B>> extends A1<A, B> { int h; } static class B2<A extends A2<A, B>, B
 * extends B2<A, B>> extends B1<A, B> { int h; } static class B2_2<A extends
 * A2<A, B>, B extends B2<A, B>> extends B2<A, B> { } //
 * ◆コアモジュールの型引数を使ってフィールドから?を除去したあと // インスタンスのgetterの型引数で?を除去してキャスト用の識別子を作る
 * static class ModuleBase<A extends A2<A, B>, B extends B2<A, B>> { static
 * ModuleBase<?, ?> api = new ModuleBase<>(); // ?除去用getter static <A extends
 * A2<A, B>, B extends B2<A, B>> ModuleBase<A, B> api() { return (ModuleBase<A,
 * B>) api; } // A a; // B b; A2<A, B> a2; // newしたものをa2に対して代入した時に安全だが、 B2<A, B>
 * b2; // a2.aは依然として変わらない void init() { a = (A) new A2<A, B>(); b = (B) new
 * B2<A, B>(); a = (A) new A2<>(); // A2がダメなので型推論しても無駄 b = (B) new B2<>(); a2 =
 * new A2<A, B>(); b2 = new B2<A, B>(); } void configure() { a.a = a; //
 * a.aの型はA、aの型はAなので代入できる a.b = b; b.a = a; b.b = b; b = (B) new B2_2<A, B>(); }
 * } // コアモジュール外からの代入 <A extends A2<A, B>, B extends B2<A, B>> void init1() {
 * ModuleBase.api.a.a.a.a.a.a.b.b.a.b.h = 4;
 * ModuleBase.api().a.a.a.a.a.a.b.b.a.b.h = 4; // フィールド参照は安全にチェーンできる
 * ModuleBase.api.b = new B2_2<>(); // ① ModuleBase.api.b = new B2_2<A, B>(); //
 * ModuleBase.api.bの型は?なので右辺をキャストできず代入出来ない ModuleBase.api().b = new B2_2<>(); //
 * ② ModuleBase.api().b = new B2_2<A, B>(); //
 * api()で宣言されている型変数の識別子が外から見えないので右辺をキャストできない // メソッドの型引数で?を除去した意味が呼び出し側からは無い
 * ((ModuleBase<A, B>) ModuleBase.api).b = (B) new B2_2<>(); //
 * 左辺をメソッド型引数でキャストすると代入ができるがgetterの意味は無い ModuleBase.api.b2 = new B2_2<>(); //
 * b2がB2<?, ?>型なのでそのまま代入できる ModuleBase.api.b2.b = new B2_2<>();
 * ModuleBase.api().b2.b = new B2_2<>(); // 表面的には代入できるようになるが、 //
 * 内部は?が含まれるのでフィールドへの代入時に①・②と同じことになる ModuleBase.<A, B> api().b2.b = (B) new
 * B2_2<>(); // api()の型引数を推論ではなく直接指定すると、キャスト可能になる } // ◆コアモジュールに?を使う静的変数を置いて //
 * 代入時のメソッド型引数に強引にキャストして代入する static class ModuleBase2 { static A2<?, ?> a;
 * static B2<?, ?> b; void init() { a = new A2<>(); // 型推論を使うと安全な代入ができる b = new
 * B2<>(); } <A extends A2<A, B>, B extends B2<A, B>> void configure() { ((A2<A,
 * B>) a).a = (A) a; // a.aの型は?なのでキャストするための ((A2<A, B>) a).b = (B) b; //
 * 識別子を作るためにメソッドが型引数を使って ((B2<A, B>) b).a = (A) a; // ?を除去してキャスト可能にする ((B2<A,
 * B>) b).b = (B) b; b = new B2_2<>(); } } //
 * コアモジュールに細工してないので外からの代入はコアモジュール内と全く同様 void init2() {
 * ModuleBase2.a.a.a.a.a.a.b.b.a.b.h = 4; ModuleBase2.b = new B2_2<>(); } //
 * ◆コアモジュールから型引数が消えつつ?を除去しようとした // コアモジュール内と外の環境が同じになる static class ModuleBase3
 * { static class API<A extends A2<A, B>, B extends B2<A, B>> { A a; B b; }
 * static API<?, ?> api = new API<>(); // 特に意味のないgetter static <A extends A2<A,
 * B>, B extends B2<A, B>> API<A, B> api() { return (API<A, B>) api; } //
 * 特に意味のないgetter static <A extends A2<A, B>, B extends B2<A, B>> A castA2(A2<A,
 * B> a2) { return (A) a2; } // <A extends A2<A, B>, B extends B2<A, B>> void
 * init() { api().a = new A2<>(); // api()のAは呼び出し側から見えない ModuleBase3.<A, B>
 * api().a = new A2<A, B>(); ModuleBase3.<A, B> api().b = new B2<>();
 * ModuleBase3.<A, B> api().a = (A) new A2<A, B>(); ModuleBase3.<A, B> api().b =
 * (B) new B2<>(); // api()の型引数を指定すると代入が可能になる // ★このメソッドのAも呼び出し側で直接指定可能なので、 //
 * ★いくら引数にAもBも無くてもA2からAへのキャストは安全なわけではない } <A extends A2<A, B>, B extends B2<A,
 * B>> void configure() { ((API<A, B>) api).a.a = ((API<A, B>) api).a; ((API<A,
 * B>) api).b.b = ((API<A, B>) api).b; ((API<A, B>) api).a.a = ((API<A, B>)
 * api).a; ((API<A, B>) api).b.b = ((API<A, B>) api).b; // 警告は出るが代入可能
 * ModuleBase3.<A, B> api().a.a = ModuleBase3.<A, B> api().a; ModuleBase3.<A, B>
 * api().b.b = ModuleBase3.<A, B> api().b; ModuleBase3.<A, B> api().a.a =
 * ModuleBase3.<A, B> api().a; ModuleBase3.<A, B> api().b.b = ModuleBase3.<A, B>
 * api().b; // 見かけは安全な代入（api()内部では安全ではない） // この書き方ではgetter1か所に警告を押し付けることができる
 * ModuleBase3.<A, B> api().b = new B2_2<>(); ModuleBase3.<A, B> api().b = new
 * B2_2<A, B>(); ModuleBase3.<A, B> api().b = (B) new B2_2<>(); //
 * こちらもBが実際はB2の子クラスの可能性があるので安全にキャストできない ModuleBase3.<A, B> api().a =
 * ModuleBase3.<A, B> castA2(new A2<A, B>()); // 警告をキャスト用のメソッド1か所に押し付けることができる
 * ModuleBase3.<A, B> api().a.a.a.a.a.a.b.b.a.b.h = 4; } } //
 * ◆新たな子クラスを作ることでクラス自体から型引数を除去しようとした static class ModuleBase4 { static class A2E
 * extends A2<A2E, B2E> { } static class B2E extends B2<A2E, B2E> { } static A2E
 * a; static B2E b; static A2<A2E, B2E> a2; static B2<A2E, B2E> b2; void init()
 * { a = new A2E(); // 当たり前に代入可能 b = new B2E(); a2 = new A2E(); b2 = new B2E();
 * a2 = new A2<A2E, B2E>(); b2 = new B2<A2E, B2E>(); } void configure() { a.a =
 * a; // こちらも当たり前のように代入できる a.b = b; b.a = a; b.b = b; a2.a = a2; //
 * a2.aはA2E型。A2<A2E, B2E>を代入できない a2.b = b2; b2.a = a2; b2.b = b2;
 * ModuleBase.api.a.a.a.a.a.a.b.b.a.b.h = 4;
 * ModuleBase.api().a.a.a.a.a.a.b.b.a.b.h = 4; } static class B2_2E extends
 * B2_2<A2E, B2E> { } void init2() { b = new B2_2E(); a.b = new B2_2E(); //
 * bと同じB2E型 // しかしB2の子実装が複数あると菱型になって代入できなくなる // そもそも互換性がないのでもはや何にキャストしてもダメになる b2
 * = new B2_2E(); // 菱型ではないので代入できる b2.b = new B2_2E(); //
 * しかしb2.bはB2E型なのでこちらは代入できない // ライブラリの設計から見直せばインターフェースを使ってうまくいくかもしれない } } //
 * ◆全て投げ出して型引数を無視 static class ModuleBase5 { static A2 a; // 型引数がないので警告になる
 * static B2 b; static B2<?, ?> b2; // 宣言時は警告が出ない void init() { a = new A2(); b
 * = new B2(); } <A extends A2<A, B>, B extends B2<A, B>> void configure() { a.a
 * = a; a.b = b; b.a = a; b.b = b; b = new B2_2(); b = new B2_2<>(); //
 * 型推論を使うと代入時は安全 a.b = new B2_2(); a.b = new B2_2<>(); b2 = new B2_2(); b2 = new
 * B2_2<>(); b2.b = new B2_2(); b2.b = new B2_2<>(); // ?を使ったことにより代入が不可能になる
 * ((B2) b2).b = new B2_2(); ((B2) b2).b = new B2_2<>(); ((B2<A, B>) b2).b = (B)
 * new B2_2(); ((B2<A, B>) b2).b = (B) new B2_2<>(); //
 * 左辺のキャストで代入ができるがこれならraw型変数を使ったほうが簡潔に書ける B2 blocal = b; //
 * しかしローカル変数宣言時にいちいち警告が出るのがうざい B2<?, ?> blocal2 = b; blocal2.a = a; //
 * ローカル変数を?にすれば警告は消えるが、使い勝手は悪い B2<A, B> blocal3 = b; blocal3.a = (A) a; //
 * 宣言時の警告は消えるが解決してない B2<> blocal4 = new B2_2<>(); // 変数の型は型推論で済ますことはできない
 * ModuleBase.api.a.a.a.a.a.a.b.b.a.b.h = 4;
 * ModuleBase.api().a.a.a.a.a.a.b.b.a.b.h = 4; } } }
 */
