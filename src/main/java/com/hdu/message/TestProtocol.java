package com.hdu.message;

import com.google.protobuf.Any;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * <p>ClassName      TestProtocol
 * <p>Description
 * <p>Author         huangqing
 * <p>Version
 * <p>Date           2020/5/30 9:55 AM
 */
public class TestProtocol {
    public static void main(String[] args) throws Exception {

        MyComplexObjectEntity.ComplexObject.Builder builder = MyComplexObjectEntity.ComplexObject.newBuilder();

        MyComplexObjectEntity.ComplexObject complexObject = null;


// 对应proto里： int32 id = 1;
        builder.setId(100);
// 对应proto里：string name = 2;//
        builder.setName("name");
// 对应proto里：string email = 3;
        builder.setEmail("email");
// 对应proto里：repeated string sons = 4; 列表
        builder.addSons("Son1");
        builder.addSons("Son2");
        builder.addSons("Son3_hq");
// 对应proto里：Gender gender = 5; // Enum值
//  builder.setGenderValue(Gender.MAN_VALUE);
        builder.setGender(MyComplexObjectEntity.Gender.MAN);
// 对应proto里：repeated Result result = 6; // 新的Result对象List
        MyComplexObjectEntity.Result.Builder r = MyComplexObjectEntity.Result.newBuilder();
        r.setTitle("title");
        builder.addResult(r.build());
// 对应proto里：repeated google.protobuf.Any any = 7; // Any对象

        Any.Builder any = Any.newBuilder();
        any.setTypeUrl("typeUrl");

        builder.addAny(any.build());
// 对应proto里：map<string, MapVaule> map_field = 8;
        MyComplexObjectEntity.MapVaule.Builder mapValue = MyComplexObjectEntity.MapVaule.newBuilder();
        mapValue.setMapValue("mapValue1");
        mapValue.setMapValue("map2");
        builder.putMap("mapKey1", mapValue.build());

        mapValue.setMapValue("mapValue1");
        mapValue.setMapValue("map2");
        builder.putMap("mapKey2", mapValue.build());


        MyComplexObjectEntity.MapVaule.Builder mapVaule1 = MyComplexObjectEntity.MapVaule.newBuilder();
        mapVaule1.setMapValue("testAny Success");
        Any any1 = Any.pack(mapVaule1.build());

        builder.addAny(any1);

        MyComplexObjectEntity.ComplexObject cob = builder.build();
        System.out.println("before :" + cob.toString());

        byte[] byteArray = cob.toByteArray();

        MyComplexObjectEntity.ComplexObject cob2 = MyComplexObjectEntity.ComplexObject.parseFrom(byteArray);
        System.out.println("after :" + cob2.toString());


//        if (any1.is(MyComplexObjectEntity.MapVaule.class)){
//            MyComplexObjectEntity.MapVaule mp= any1.unpack(MyComplexObjectEntity.MapVaule.class);
//            System.out.println(mp.getMapValue());
//        }

    }

}
