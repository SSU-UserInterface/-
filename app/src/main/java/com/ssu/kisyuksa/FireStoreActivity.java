package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireStoreActivity extends AppCompatActivity {
    private static final String TAG = "DocSnippets";

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "setCotentView 전");
        setContentView(R.layout.activity_temp_for_firestore);

        initializeCloudFirestore();

        addData();Log.d("TAG","addData() 실행후");
        addData_delivery();
        addData_delivery_lunch();
        addData_delivery_dinner();
        addData_ott_netFlix();
        addData_ott_tving();
        addData_chating();
//        addData_chat();
        //        getADocument();   // Document관련


//        addDataFromCustomObject();
//        getDocumentAsCustomObject();

//        getMultipleDocumentsFromACollection();    /// 조건 맞는 것만 출력
//        getAllDocumentsInACollection();

//        createSubCollectionWithTheSameID();
//        collectionGroupQuery();

//        getDocumentsWidthOrderAndLimitData();
    }

    private void initializeCloudFirestore() {
        // Access a Cloud Firestore instance from your Activity
        db = FirebaseFirestore.getInstance();   ///firebase store 객체
    }
    public void addDataOne(String string1, String string2) {
        Log.d("TAG", "addDataOne 실행");
        CollectionReference cities = db.collection("cities");
        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", string1);
        data1.put("state", string1);
        data1.put("country", "addDataOne");
        data1.put("capital", false);
        data1.put("population", 860000);
        data1.put("regions", Arrays.asList("west_coast", "norcal"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("document1").set(data1);       //document 하나가 올라갔다
    }

    public void addData_delivery() {
        Log.d("TAG","addData_delivery 실행");
        CollectionReference cities = db.collection("delivery_branch");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("menu", "pizza");
        data1.put("numText", 3);
        data1.put("maxNum", 5);
        data1.put("charRoom", "3번방");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pizza").set(data1);

// 추가 데이터 예시
        Map<String, Object> data2 = new HashMap<>();
        data2.put("menu", "pasta");
        data2.put("numText", 2);
        data2.put("maxNum", 4);
        data2.put("charRoom", "2번방");
        data2.put("list", Arrays.asList("item1", "item2", "item3"));
        data2.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pasta").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("menu", "burger");
        data3.put("numText", 5);
        data3.put("maxNum", 8);
        data3.put("charRoom", "5번방");
        data3.put("list", Arrays.asList("cheese", "bacon", "lettuce"));
        data3.put("timestamp", FieldValue.serverTimestamp());
        cities.document("burger").set(data3);   //document 하나가 올라갔다
    }
    public void addData_delivery_lunch() {
        Log.d("TAG","addData_delivery 실행");
        CollectionReference cities = db.collection("delivery_lunch");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("menu", "pizza");
        data1.put("numText", 3);
        data1.put("maxNum", 5);
        data1.put("charRoom", "3번방");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pizza").set(data1);

// 추가 데이터 예시
        Map<String, Object> data2 = new HashMap<>();
        data2.put("menu", "pasta");
        data2.put("numText", 2);
        data2.put("maxNum", 4);
        data2.put("charRoom", "2번방");
        data2.put("list", Arrays.asList("item1", "item2", "item3"));
        data2.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pasta").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("menu", "burger");
        data3.put("numText", 5);
        data3.put("maxNum", 8);
        data3.put("charRoom", "5번방");
        data3.put("list", Arrays.asList("cheese", "bacon", "lettuce"));
        data3.put("timestamp", FieldValue.serverTimestamp());
        cities.document("burger").set(data3);   //document 하나가 올라갔다
    }

    public void addData_delivery_dinner() {
        Log.d("TAG","addData_delivery 실행");
        CollectionReference cities = db.collection("delivery_dinner");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("menu", "pizza");
        data1.put("numText", 3);
        data1.put("maxNum", 5);
        data1.put("charRoom", "3번방");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pizza").set(data1);

// 추가 데이터 예시
        Map<String, Object> data2 = new HashMap<>();
        data2.put("menu", "pasta");
        data2.put("numText", 2);
        data2.put("maxNum", 4);
        data2.put("charRoom", "2번방");
        data2.put("list", Arrays.asList("item1", "item2", "item3"));
        data2.put("timestamp", FieldValue.serverTimestamp());
        cities.document("pasta").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("menu", "burger");
        data3.put("numText", 5);
        data3.put("maxNum", 8);
        data3.put("charRoom", "5번방");
        data3.put("list", Arrays.asList("cheese", "bacon", "lettuce"));
        data3.put("timestamp", FieldValue.serverTimestamp());
        cities.document("burger").set(data3);   //document 하나가 올라갔다
    }
    public void addData() {
        Log.d("TAG","addData 실행");
        CollectionReference cities = db.collection("cities");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "San Francisco");
        data1.put("state", "CA");
        data1.put("country", "USA");
        data1.put("capital", false);
        data1.put("population", 860000);
        data1.put("regions", Arrays.asList("west_coast", "norcal"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("SF").set(data1);       //document 하나가 올라갔다

        Map<String, Object> data2 = new HashMap<>();
        data2.put("name", "Los Angeles");
        data2.put("state", "CA");
        data2.put("country", "USA");
        data2.put("capital", false);
        data2.put("population", 3900000);
        data2.put("regions", Arrays.asList("west_coast", "socal"));
        data2.put("timestamp", FieldValue.serverTimestamp());
        cities.document("LA").set(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("name", "Washington D.C.");
        data3.put("state", null);
        data3.put("country", "USA");
        data3.put("capital", true);
        data3.put("population", 680000);
        data3.put("regions", Arrays.asList("east_coast"));
        data3.put("timestamp", FieldValue.serverTimestamp());
        cities.document("DC").set(data3);

        Map<String, Object> data4 = new HashMap<>();
        data4.put("name", "Tokyo");
        data4.put("state", null);
        data4.put("country", "Japan");
        data4.put("capital", true);
        data4.put("population", 9000000);
        data4.put("regions", Arrays.asList("kanto", "honshu"));
        data4.put("timestamp", FieldValue.serverTimestamp());
        cities.document("TOK").set(data4);

        Map<String, Object> data5 = new HashMap<>();
        data5.put("name", "Beijing");
        data5.put("state", null);
        data5.put("country", "China");
        data5.put("capital", true);
        data5.put("population", 21500000);
        data5.put("regions", Arrays.asList("jingjinji", "hebei"));
        data5.put("timestamp", FieldValue.serverTimestamp());
        cities.document("BJ").set(data5);

        Map<String, Object> data6 = new HashMap<>();
        data6.put("name", "Seoul");
        data6.put("state", null);
        data6.put("country", "Korea");
        data6.put("capital", true);
        data6.put("population", 21500000);
        data6.put("regions", Arrays.asList("west_coast", "Seoul"));
        data6.put("timestamp", FieldValue.serverTimestamp());
        cities.document("SE").set(data6);

        Map<String, Object> data7 = new HashMap<>();
        data7.put("name", "Pusan");
        data7.put("state", null);
        data7.put("country", "Korea");
        data7.put("capital", true);
        data7.put("population", 21500000);
        data7.put("regions", Arrays.asList("south", "Pusan"));
        data7.put("timestamp", FieldValue.serverTimestamp());
        cities.document("PU").set(data7);

        Map<String, Object> data8 = new HashMap<>();
        data8.put("name", "Incheon");
        data8.put("state", null);
        data8.put("country", "Korea");
        data8.put("capital", true);
        data8.put("population", 21500000);
        data8.put("regions", Arrays.asList("west", "Incheon"));
        data8.put("timestamp", FieldValue.serverTimestamp());
        cities.document("IN").set(data8);

    }
    public void addData_ott_netFlix() {
        Log.d("TAG","addData_ott실행");
        CollectionReference ottNetFlix = db.collection("ott_netFlix");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("title", "k넷플리스 자리 원해요~~");
        data1.put("content", "기간은 3달정도 생각하고 있어요!!");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        ottNetFlix.document("data1").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data1.put("title", "s넷플리스 파티원 모집중");
        data1.put("content", "선착순 2분 마감");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        ottNetFlix.document("data2").set(data1);
    }

    public void addData_ott_tving() {
        Log.d("TAG","addData_ott실행");
        CollectionReference ottNetFlix = db.collection("ott_tving");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("title", "ott_tving 자리 원해요~~");
        data1.put("content", "기간은 3달정도 생각하고 있어요!!");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        ottNetFlix.document("data1").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data1.put("title", "ott_tving 파티원 모집중");
        data1.put("content", "선착순 2분 마감");
        data1.put("list", Arrays.asList("list1", "list2"));
        data1.put("timestamp", FieldValue.serverTimestamp());
        ottNetFlix.document("data2").set(data1);
    }

    private void addData_chat() {
        Log.d("TAG", "addData_chat 실행");
        CollectionReference chatMessages = db.collection("chats"); // 수정: 컬렉션 이름을 chatMessages로 변경

        Map<String, Object> data1 = new HashMap<>();
        data1.put("mName", "isaac");
        data1.put("mMessage", "hello!");
        data1.put("mUid", "12345g");
        data1.put("timestamp", FieldValue.serverTimestamp()); // 수정: timestamp 추가
        chatMessages.document("doc1").set(data1);


        Map<String, Object> data2 = new HashMap<>();
        data2.put("mName", "yelin");
        data2.put("mMessage", "hello!");
        data2.put("mUid", "1004");
        data2.put("timestamp", FieldValue.serverTimestamp()); // 수정: timestamp 추가
        chatMessages.document("doc2").set(data2);

    }

    public void addData_chating() {
        Log.d("TAG","addData_chating 실행");
        CollectionReference cities = db.collection("chating");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "Jini");
        data1.put("message", "Hi Harry");
        data1.put("timestamp", FieldValue.serverTimestamp());
        cities.document("doc1").set(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("name", "Harry");
        data2.put("message", "you look good");
        data2.put("timestamp", FieldValue.serverTimestamp());
        cities.document("doc2").set(data2);
    }


    private void getADocument() {
        DocumentReference docRef = db.collection("cities").document("SF");  ///컬랙션, document, data
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //document 레퍼런스 get() --> 리스너 달아준다.
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {  // 특정 문서를 가져오는 것
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();

                        Log.d("TAG", "DocumentSnapshot data: " + data);

                        String country = (String) data.get("country");      // 데이타에서 필드이름을 넣어주면 값이 나온다.
                        boolean isCapital = (boolean) data.get("capital");
                        List<String> regions = (List<String>) data.get("regions");  // 배열인 것들은 리스트로 빼 낸다.
                        for (String region: regions) {
                            Log.d("TAG", "region = " + region);
                        }
                        String name = (String) data.get("name");
                        String state = (String) data.get("state");
                        long population = (long) data.get("population");
                        Timestamp timestamp = (Timestamp) data.get("timestamp");
                        Log.d("TAG", "timestamp = " + timestamp);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }
    //개수 제한을 둔다
    private void getDocumentsWidthOrderAndLimitData() {     /// 개수 제한을 준다.
        db.collection("cities").orderBy("name").limit(3).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot result = task.getResult();
                List<DocumentSnapshot> documents = result.getDocuments();
                for (DocumentSnapshot doc: documents) {
                    Log.d("TAG", doc.getId() + ": " + doc.getData());
                }
            }
        });
    }

    private void getMultipleDocumentsFromACollection() {
        db.collection("cities")     /// citys를 고른 거임
                .whereEqualTo("capital", true)      /// capital이라는 속성(수도)이 true인 것만 가져오겠다. ,여러개 [조건을 거는 것]
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void getAllDocumentsInACollection() {       /// 위에서 [조건]을 제거하는 것
        db.collection("cities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

//    // 정보를 덮어쓰기!!
//    private void addDataFromCustomObject() {    /// 정보를 덮어쓰기!!  /// timestamp
//        City city = new City("Los Angeles", "CA", "USA",
//                false, 5000000L, Arrays.asList("west_coast", "sorcal"));
//        db.collection("cities").document("LA").set(city);       /// 덮어쓰기
//    }
//
//    // 문서데이터(documentSnapshot 자료형)을 City 자료형으로 변환한 뒤 그 안의 필드를 사용!
//    private void getDocumentAsCustomObject() {  ///원래는 map으로 나왔는데 객체로 받는다?
//        DocumentReference docRef = db.collection("cities").document("LA");
//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                City city = documentSnapshot.toObject(City.class);      /// 어떤 클래스의 객체로 바꿀건지 명시
//
//                long population = city.getPopulation();
//                Log.d("TAG", "population = " + population);
//                Timestamp timestamp = city.getTimestamp();
//                Log.d("TAG", "timestamp = " + timestamp.toDate().toString());   //여기서 에러인듯
//                Log.d("TAG", "위의 문장 호출 안 됨?");
//            }
//        });
//    }

    private void createSubCollectionWithTheSameID() {   /// 서브 컬랙션을 만들 때, 랜드마크, document를 명시하지 않으면 자동 생성,
        CollectionReference citiesRef = db.collection("cities");

        Map<String, Object> ggbData = new HashMap<>();
        ggbData.put("name", "Golden Gate Bridge");
        ggbData.put("type", "bridge");
        citiesRef.document("SF").collection("landmarks").add(ggbData);

        Map<String, Object> lohData = new HashMap<>();
        lohData.put("name", "Legion of Honor");
        lohData.put("type", "museum");
        citiesRef.document("SF").collection("landmarks").add(lohData);

        Map<String, Object> gpData = new HashMap<>();
        gpData.put("name", "Griffith Park");
        gpData.put("type", "park");
        citiesRef.document("LA").collection("landmarks").add(gpData);

        Map<String, Object> tgData = new HashMap<>();
        tgData.put("name", "The Getty");
        tgData.put("type", "museum");
        citiesRef.document("LA").collection("landmarks").add(tgData);

        Map<String, Object> lmData = new HashMap<>();
        lmData.put("name", "Lincoln Memorial");
        lmData.put("type", "memorial");
        citiesRef.document("DC").collection("landmarks").add(lmData);

        Map<String, Object> nasaData = new HashMap<>();
        nasaData.put("name", "National Air and Space Museum");
        nasaData.put("type", "museum");
        citiesRef.document("DC").collection("landmarks").add(nasaData);

        Map<String, Object> upData = new HashMap<>();
        upData.put("name", "Ueno Park");
        upData.put("type", "park");
        citiesRef.document("TOK").collection("landmarks").add(upData);

        Map<String, Object> nmData = new HashMap<>();
        nmData.put("name", "National Museum of Nature and Science");
        nmData.put("type", "museum");
        citiesRef.document("TOK").collection("landmarks").add(nmData);

        Map<String, Object> jpData = new HashMap<>();
        jpData.put("name", "Jingshan Park");
        jpData.put("type", "park");
        citiesRef.document("BJ").collection("landmarks").add(jpData);

        Map<String, Object> baoData = new HashMap<>();
        baoData.put("name", "Beijing Ancient Observatory");
        baoData.put("type", "museum");
        citiesRef.document("BJ").collection("landmarks").add(baoData);
    }


}