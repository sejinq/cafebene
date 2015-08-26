package cosmantic.cosmantic_khw;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * <b>Parse.com</b>의 서버와 통신을 담당하는 클래스<br/>
 * 주로 서버의 <b>데이터베이스</b>에 데이터를 <b>저장, 로드</b> 액션을 한다.<br/>
 * 데이터에는 회원, 상품, 리뷰 등이 있다.
 */
public class ServerInteraction {
    //임시로 설정
    static int[] skinType = new int[3];
    static int[] effects = new int[3];
    private static User instant_user = new User(0, "sejin", "sejin", "sejinq", true, 21, 0, skinType);

    /**
     * 로그인 액션에서 사용되는 플래그들이 속해있는 인터페이스<br/>
     *
     * <br/><b><i>플래그 : </i></b><br/>
     * <pre><b>SUCCESS</b> - 로그인 성공 플래그(0x0)<br/></pre>
     * <pre><b>ID_MISMATCH</b> - 로그인 실패; 존재하지 않는 아이디 플래그(0x1)<br/></pre>
     * <pre><b>PW_MISMATCH</b> - 로그인 실패; 일치하지 않는 비밀번호 플래그(0x2)</pre>
     */
    public interface loginFlag{
        int SUCCESS = 0x0;	   //로그인 성공
        int ID_MISMATCH = 0x1; //아이디 없음
        int PW_MISMATCH = 0x2; //비밀번호 불일치
        int UNKNOWN_ERROR = 0x3;//원인을 모르는 에러
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
    public static int onLogin(String id, String password, int userType){
        String objectId = null;
        if(userType == User.UserType.EMAIL){
            try {
                objectId =  ParseUser.logIn(id, password).getObjectId();
            }catch(ParseException e){
                if(e.getCode() == ParseException.USERNAME_MISSING) return loginFlag.ID_MISMATCH;
                else if(e.getCode() == ParseException.PASSWORD_MISSING) return loginFlag.PW_MISMATCH;
                else return loginFlag.UNKNOWN_ERROR;
            }
        }else{

        }
        return loginFlag.SUCCESS;
    }
    public static User onLoginWithParseUser(ParseUser parseUser){
        User loginUser = new User();
        String nickName = parseUser.getString("displayedName");
        if(nickName != null) {
            loginUser.setUsername(parseUser.getUsername());
            loginUser.setDisplayedName(nickName);
            loginUser.setAge(parseUser.getNumber("age").intValue());
            loginUser.setGender(parseUser.getBoolean("gender"));
            JSONArray JSONSkinProblems = parseUser.getJSONArray("skinProblem");
            int[] skinProblems = new int[JSONSkinProblems.length()];
            try {
                for (int loop = 0; loop < JSONSkinProblems.length(); loop++)
                    skinProblems[loop] = JSONSkinProblems.getInt(loop);
                loginUser.setSkinProblem(skinProblems);
            } catch (Exception e) {
                Log.e("Sign In", "Error in get skin problem:" + e.getMessage());
                return null;
            }
            loginUser.setSkinType(parseUser.getNumber("skinType").intValue());
            if(parseUser.getParseFile("profilePicture")!=null)
                loginUser.setImage(parseUser.getParseFile("profilePicture").getDataInBackground().getResult());
            else loginUser.setImage(null);

            List<String> likeList = parseUser.getList("like");
            loginUser.initLike(likeList);

            return loginUser;
        }else{
            return null;
        }
    }
    //로그아웃 액션
    public static boolean onLogout(){
        ParseUser.logOut();

        return true;
    }
    //가입 액션 플래그
    public interface signUpFlag{
        int SUCCESS = 0x0; //가입 성공
        int FAILURE = 0x1; //가입 실패

    }
    //가입 액션
    public static int onSignUp(User user, int userType){
        ParseUser newUser;
        if(userType == User.UserType.EMAIL) {
            newUser = new ParseUser();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
        }else{
            newUser = ParseUser.getCurrentUser();
        }
        newUser.put("gender", user.getGender());
        newUser.put("age", user.getAge());
        newUser.put("skinType", user.getSkinType());
        int[] skinProblemArray = user.getSkinProblem();
        JSONArray skinProblemJSON = new JSONArray();
        for(int loop = 0; loop < skinProblemArray.length; loop++) skinProblemJSON.put(skinProblemArray[loop]);
        newUser.put("skinProblem", skinProblemJSON);
        newUser.put("userType", user.getUserType());
        newUser.put("like", new JSONArray());
        newUser.put("displayedName", user.getDisplayedName());
        try {
            if(userType == User.UserType.EMAIL)
                newUser.signUp();
            else
                newUser.save();
        }catch(ParseException e){
            e.getCode();
            return signUpFlag.FAILURE;
        }
        return signUpFlag.SUCCESS;
    }
    //회원 정보 반환 메소드
    public static Review[] getReviewInform(String productId, String userId){
        ParseQuery<ParseObject> reviewQuery = ParseQuery.getQuery("reviewData");
        if(userId != null) reviewQuery.whereEqualTo("userId", userId);
        if(productId != null) reviewQuery.whereEqualTo("productId", productId);
        try {
            List<ParseObject> reviewList = reviewQuery.find();
            Review[] reviews = new Review[reviewList.size()];
            for(int loop = 0; loop < reviewList.size(); loop++){
                ParseObject reviewData = reviewList.get(loop);
                reviews[loop] = new Review();
                reviews[loop].setUserObjectId(reviewData.getString("userId"));
                reviews[loop].setProductObjectId(reviewData.getString("productId"));
                reviews[loop].setRate(reviewData.getDouble("productId"));
                reviews[loop].setContent(reviewData.getString("content"));
            }
            return reviews;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 상품 정보 반환 메소드
     * 인자: int product_id - 상품 식별자
     */
    /**
     *
     */
    public static String[] getMainRecommendList(int skin_type){
        Product[] products = new Product[9];
        ParseQuery<ParseObject> recommandQuery = ParseQuery.getQuery("recommendData");
        if(skin_type==User.SKIN_TYPE_DRY)
            recommandQuery.whereEqualTo("recommendedName","Dry");
        else if(skin_type==User.SKIN_TYPE_OILY)
            recommandQuery.whereEqualTo("recommendedName","Oily");
        else if(skin_type==User.SKIN_TYPE_SENSITIVE)
            recommandQuery.whereEqualTo("recommendedName","Sensitive");
        else {
            String[] unknown = {"Dry","Oily","Sensitive"};
            recommandQuery.whereContainedIn("recommendedName",Arrays.asList(unknown));
        }
        try {
            recommandQuery.selectKeys(Arrays.asList("recommendedList"));
            List<Object> recommendedID;
            if(skin_type==User.SKIN_TYPE_UNKNOWN){
                ParseObject objects = recommandQuery.find().get(0);
                recommendedID = objects.getList("recommendedList");
                objects = recommandQuery.find().get(1);
                recommendedID.addAll(objects.getList("recommendedList"));
                objects = recommandQuery.find().get(2);
                recommendedID.addAll(objects.getList("recommendedList"));
            }else {
                ParseObject objects = recommandQuery.find().get(0);
                recommendedID = objects.getList("recommendedList");
            }
            long seed = System.nanoTime();
            Collections.shuffle(recommendedID,new Random(seed));
            String[] productIds = new String[9];
            for(int loop=0; loop<9;loop++) {
                Log.d("Main Recommend","Get Main Recommend:"+skin_type+"("+loop+")"+recommendedID.get(loop));
                productIds[loop] = (String)recommendedID.get(loop);
//                products[loop] = getProductInform(recommendedID.get(loop));
//                context.recommendApply(products);
            }
            return productIds;
        }catch(Exception e){
            e.printStackTrace();
        }
            return null;
    }

    /**
     *
     * @param skin_type
     * @return
     */
    public static String[] getRecommendList(int skin_type){
        ParseQuery<ParseObject> recommandQuery = ParseQuery.getQuery("recommendData");
        if(skin_type==User.SKIN_TYPE_DRY)
            recommandQuery.whereEqualTo("recommendedName","Dry");
        else if(skin_type==User.SKIN_TYPE_OILY)
            recommandQuery.whereEqualTo("recommendedName","Oily");
        else if(skin_type==User.SKIN_TYPE_SENSITIVE)
            recommandQuery.whereEqualTo("recommendedName","Sensitive");
        else if(skin_type==User.SEASON_SOLDIER)
            recommandQuery.whereEqualTo("recommendedName","Soldier");

        try {
            recommandQuery.selectKeys(Arrays.asList("recommendedList"));
            List<String> recommendedID;
            ParseObject objects = recommandQuery.find().get(0);
            recommendedID = objects.getList("recommendedList");

            String[] productIds = new String[recommendedID.size()];
            for(int loop=0; loop<recommendedID.size();loop++) {
                Log.d("Main Recommend","Get Recommend Activity:"+skin_type+"("+loop+")"+recommendedID.get(loop));
                productIds[loop] = recommendedID.get(loop);
            }
            return productIds;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    public static String[] getRelaventProduct(Product productId){
        String[] relaventProduct = new String[3];
        ParseQuery<ParseObject> relaventQuery = ParseQuery.getQuery("cosmeticData");
        relaventQuery.whereEqualTo("category", productId.getType());
        try{
            List<ParseObject> results = relaventQuery.find();
            long seed = System.nanoTime();
            Collections.shuffle(results,new Random(seed));
            for(int loop=0; loop<3;loop++){
                String pid = results.get(loop).getObjectId();
                if(pid.equals(productId.getObjectId())&results.size()>=4)
                    relaventProduct[loop] = results.get(loop).getObjectId();
                else relaventProduct[loop] = pid;
            }
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }

        return relaventProduct;
    }
//    String productName, int price, String brand, String size, double pricePerSize, String description, int[] effects, String skintype, String type, String curatingInfo, float score, int reviewNum
    public static Product getProductInform(String productObjectId){
        Product findProduct = new Product();
        ParseObject productObject = null;
        try {
            ParseQuery<ParseObject> productQuery = ParseQuery.getQuery("cosmeticData");
            productQuery.whereEqualTo("objectId", productObjectId);
            productObject = productQuery.find().get(0);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if(productObject == null){
            findProduct = null;
        }

        findProduct.setObjectId(productObject.getObjectId());
        findProduct.setBrand(productObject.getString("brand"));
        findProduct.setBrandKor(productObject.getString("brandKor"));
        findProduct.setCuratingInfo(productObject.getString("curation"));
        List<String> effetsList = productObject.getList("effect");
        int[] effects = new int[effetsList.size()];
        for(int effect_loop = 0; effect_loop<effetsList.size(); effect_loop++){
            String effect = effetsList.get(effect_loop);
            if(effect.equals("보습")) effects[effect_loop] = User.INEREST_SHARE;
            else if(effect.equals("여드름개선")) effects[effect_loop] = User.INEREST_TROUBLE;
            else if(effect.equals("주름개선")) effects[effect_loop] = User.INEREST_WRINKLE;
            else if(effect.equals("모공축소")) effects[effect_loop] = User.INEREST_PORE;
            else if(effect.equals("자외선차단")) effects[effect_loop] = User.INEREST_SUN;
            else if(effect.equals("미백")) effects[effect_loop] = User.INEREST_WHITE;
            else if(effect.equals("피부재생")) effects[effect_loop] = User.INEREST_REPAIR;
            else if(effect.equals("각질제거")) effects[effect_loop] = User.INEREST_SCRUB;
        }
        findProduct.setEffects(effects);
        findProduct.setPrice(productObject.getInt("price"));
        findProduct.setPricePerSize(productObject.getDouble("price_size"));
        findProduct.setProductName(productObject.getString("productName"));
        findProduct.setSize(productObject.getString("size"));
        ParseFile fileName = productObject.getParseFile("thumbnail");
        try {
            if (fileName != null) findProduct.setThumnail(fileName.getData());
            else findProduct.setThumnail(null);
        }catch(ParseException e){
            e.printStackTrace();
            findProduct.setThumnail(null);
        }
        findProduct.setType(productObject.getString("category"));
        findProduct.setDescription(productObject.getString("description"));
        findProduct.setIngredientCount(new int[]{productObject.getInt("oilyPos"), productObject.getInt("oilyNeg"),
                productObject.getInt("dryPos"), productObject.getInt("dryNeg"),
                productObject.getInt("sensetivePos"), productObject.getInt("sensetiveNeg")});
        //평균 점수
        if(findProduct.getObjectId().equals("Zgm7xKsOPF")) Log.d("Review", "AVG Star test start");
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productId", findProduct.getObjectId());
        try {
            float avg = 0.0f;//((HashMap<String,JSONObject>)ParseCloud.callFunction("averageStars", params)).get("result");
            findProduct.setScore(avg);
            if(findProduct.getObjectId().equals("Zgm7xKsOPF")) Log.d("Review", "AVG Star test End:"+avg);
        }catch(Exception e){
            e.printStackTrace();if(findProduct.getObjectId().equals("Zgm7xKsOPF")) Log.d("Review", "AVG Star test Error:");

            findProduct.setScore(0.0f);
        }

        ParseQuery<ParseObject> countQuery = ParseQuery.getQuery("reviewData");
        countQuery.whereEqualTo("productId", findProduct.getObjectId());
        try {
            int count = countQuery.count();
            Log.d("Review", "pid:"+findProduct.getObjectId()+", count:"+count);
            findProduct.setReviewNum(count);
        }catch(ParseException e){
            e.printStackTrace();
            findProduct.setReviewNum(0);
        }

        Log.d("Product","Get:"+findProduct.getBrandKor()+", "+findProduct.getProductName()+", "+findProduct.getSize());

        return findProduct;
    }
    public static User getUserInform(String user_id){
        return instant_user;
    }
    public static Product[] searchProduct(String key_word){
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery("cosmeticData");
        pQuery.whereMatches("productName", ".*" + key_word + ".*", "i");

        try {
            List<ParseObject> results = pQuery.find();
            Product[] products = new Product[results.size()];
            for(int loop=0; loop<results.size(); loop++)
                products[loop] = getProductInform(results.get(loop).getObjectId());
            return products;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Brand[] searchBrand(String key_word){
        ParseQuery<ParseObject> pQuery = ParseQuery.getQuery("brandData");
        pQuery.whereMatches("name", ".*" + key_word + ".*", "i");

        try {
            List<ParseObject> results = pQuery.find();
            Brand[] brands = new Brand[results.size()];
            for(int loop=0; loop<results.size(); loop++) {
                String name = results.get(loop).getString("name");
                byte[] thumb = results.get(loop).getParseFile("thumb").getData();
                String id = results.get(loop).getObjectId();
                ParseQuery<ParseObject> countQuery = ParseQuery.getQuery("cosmeticData");
                countQuery.whereEqualTo("brand", results.get(loop).get("name"));
                int count = countQuery.count();
                brands[loop] = new Brand(id, thumb, name, count);
            }
            return brands;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Product[] searchProductInBrand(String brandName){
        ParseQuery<ParseObject> searchQuery = ParseQuery.getQuery("cosmeticData");
        searchQuery.whereEqualTo("brand", brandName);
        try{
            List<ParseObject> results = searchQuery.find();
            Product[] products = new Product[results.size()];
            for(int loop = 0; loop < results.size(); loop++)
                products[loop] = getProductInform(results.get(loop).getObjectId());
            return products;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public static boolean compareNickName(String nickName){
        ParseQuery<ParseObject> countQuery = ParseQuery.getQuery("_User");
        countQuery.whereEqualTo("displayedName", nickName);
        int count;
        try {
            count = countQuery.count();
            Log.d("Nick Name", "check:" + nickName + "," + count);
        }catch(ParseException e){
            e.printStackTrace();
            return false;
        }
        if(count==0) return true;
        return false;
    }
    public static boolean compareUserName(String userName){
        ParseQuery<ParseObject> countQuery = ParseQuery.getQuery("_User");
        countQuery.whereEqualTo("userName", userName);
        int count;
        try {
            count = countQuery.count();
            Log.d("User Name", "check:" + userName + "," + count);
        }catch(ParseException e){
            e.printStackTrace();
            return false;
        }
        if(count==0) return true;
        return false;
    }
    public static boolean onReviewUpload(Review review){ //인자는 좀 더 고민해보고 채워넣을 예정
        ParseObject reviewObject = new ParseObject("reviewData");
        Log.d("Review Upload","pid:"+review.getProductObjectId()+", uid:"+review.getUserObjectId()+", rate:"+review.getRate()+", content:"+review.getContent());
        reviewObject.put("productId", review.getProductObjectId());
        reviewObject.put("userId",review.getUserObjectId());
        reviewObject.put("content", review.getContent());
        reviewObject.put("rate", review.getRate());
        try {
            reviewObject.save();
            return true;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void getWebContentsList(int contentType, InfoDetailActivity activity){
        //Query Define
        ParseQuery<ParseObject> contentQuery = ParseQuery.getQuery("webContents");
        contentQuery.whereEqualTo("type", contentType);
        if(contentType == InformationActivity.web_pageFlag.PG_PRODUCT_REVIEW) contentQuery.whereExists("image");
        contentQuery.selectKeys(Arrays.asList("objectId"));
        Log.d("Information","Get List of data");
        //Get Data
        try{
            List<ParseObject> objectLists = contentQuery.find();
            String[] idArray = new String[objectLists.size()];
            for(int loop = 0; loop<objectLists.size(); loop++){
                idArray[loop] = objectLists.get(loop).getObjectId();
            }
            Log.d("Information","Success to get List of data");
            activity.showWeb(idArray);
        }catch(ParseException e){
            Log.d("Information","Fail to get List of data");
            e.printStackTrace();
        }
    }

    public static WebContents getWebContentInform(String contentsObjectId){
        //Query Define
        ParseQuery<ParseObject> contentQuery = ParseQuery.getQuery("webContents");
        contentQuery.whereEqualTo("objectId", contentsObjectId);
        Log.d("Information", "Get data");
        //Get Data
        try {
            ParseObject contentParseObject = contentQuery.find().get(0);
            WebContents content = new WebContents((contentParseObject.getParseFile("image") == null) ? null : contentParseObject.getParseFile("image").getData(),
                    contentParseObject.getString("title"),contentParseObject.getString("subTitle"),contentParseObject.getString("productObjectId"),contentParseObject.getString("url"));
            Log.d("Information","Success to get data");
            return content;
        }catch(ParseException e){
            Log.d("Information","Fail to get data");
            e.printStackTrace();
            return null;
        }
    }
}