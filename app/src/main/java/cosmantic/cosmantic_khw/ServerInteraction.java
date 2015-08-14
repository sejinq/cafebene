package cosmantic.cosmantic_khw;

/**
 * <b>Parse.com</b>의 서버와 통신을 담당하는 클래스<br/>
 * 주로 서버의 <b>데이터베이스</b>에 데이터를 <b>저장, 로드</b> 액션을 한다.<br/>
 * 데이터에는 회원, 상품, 리뷰 등이 있다.
 */
public class ServerInteraction {
	/**
	 * 로그인 액션에서 사용되는 플래그들이 속해있는 인터페이스<br/>
	 *
	 * <br/><b><i>플래그 : </i></b><br/>
	 * <pre><b>SUCCESS</b> - 로그인 성공 플래그(0x0)<br/></pre>
	 * <pre><b>ID_MISMATCH</b> - 로그인 실패; 존재하지 않는 아이디 플래그(0x1)<br/></pre>
	 * <pre><b>PW_MISMATCH</b> - 로그인 실패; 일치하지 않는 비밀번호 플래그(0x2)</pre>
	 */
	public interface loginFlag{
		public static final int SUCCESS = 0x0;		 //로그인 성공
		public static final int ID_MISMATCH = 0x1; //아이디 없음
		public static final int PW_MISMATCH = 0x2; //비밀번호 불일치
	}
	/**
	 * 서버와 통신하여 로그인을하는 메소드<br/>
	 *
	 * @param id 로그인할 아이디<br/>
	 * @param password 로그인할 비밀번호<br/>
	 *
	 * @return <b>loginFlag</b> 인터페이스의 플래그 중 하나<br/>
	 * <b>SUCCESS, ID_MISMATCH, PW_MISMATCH</b> 반환
	 */
	public int onLogin(String id, String password){
		return loginFlag.SUCCESS;
	}
	//로그아웃 액션
	public boolean onLogout(){
		return false;
	}
	//가입 액션 플래그
	public interface signUpFlag{
		public static final int SUCCESS = 0x0; //가입 성공
	}
	//가입 액션
	public int onSignUp(){
		return signUpFlag.SUCCESS;
	}
	//회원 정보 반환 메소드
	public User getUserInform(){
		return null;
	}
	/**
	 * 상품 정보 반환 메소드
	 * 인자: int product_id - 상품 식별자
	 */
	public Product getProductInform(int product_id){
		return null;
	}
	/**
	 *
	 */
	public Product[] getMainRecommendList(int skin_type){
		return null;
	}

	/**
	 *
	 * @param skin_type
	 * @return
	 */
	public Product[] getRecommendList(int skin_type){
		return null;
	}

	public void searchProduct(String key_word){

	}

	public static boolean onReviewUpload(Review review){ //인자는 좀 더 고민해보고 채워넣을 예정
		return false;
	}
}

