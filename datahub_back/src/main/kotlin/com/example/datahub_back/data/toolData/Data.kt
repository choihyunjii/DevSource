package com.example.datahub_back.data.toolData
import com.example.datahub_back.dto.toolDTO.*
import java.time.LocalDateTime

val exampleProfile = Profile(1,"주동호","010-7761-8482","launcher37@naver.com","1234")
val exampleProfile1 = Profile(1, "주동호", "010-7761-8482", "launcher37@naver.com", "1234")
val exampleProfile2 = Profile(2, "John Doe", "123-456-7890", "john.doe@example.com", "password")
val exampleProfile3 = Profile(3, "Jane Doe", "987-654-3210", "jane.doe@example.com", "password")
val exampleProfile4 = Profile(4, "이순신", "010-1234-5678", "soonshin@example.com", "password")

val exampleProjectMembers1 = mutableListOf(exampleProfile1, exampleProfile2)
val exampleProjectMembers2 = mutableListOf(exampleProfile3)
val exampleProjectMembers3 = mutableListOf(exampleProfile4)

val exampleProject1 = Project(
    1,
    "학교 관리 웹 사이트",
    "학교를 관리하는 웹 서비스입니다.",
    LocalDateTime.now(),
    LocalDateTime.now(),
    2,
    2,
    exampleProjectMembers1,
    exampleProfile1
)
val exampleProject2 = Project(
    2,
    "온라인 쇼핑 앱",
    "다양한 상품을 판매하는 온라인 쇼핑 앱입니다.",
    LocalDateTime.now(),
    LocalDateTime.now(),
    1,
    1,
    exampleProjectMembers2,
    exampleProfile1
)

val exampleProject3 = Project(
    3,
    "사진 공유 플랫폼",
    "사진을 공유하고 소통하는 플랫폼입니다.",
    LocalDateTime.now(),
    LocalDateTime.now(),
    1,
    1,
    exampleProjectMembers3,
    exampleProfile1
)

val exampleProject4 = Project(
    4,
    "온라인 강의 플랫폼",
    "다양한 주제의 온라인 강의를 제공하는 플랫폼입니다.",
    LocalDateTime.now(),
    LocalDateTime.now(),
    1,
    1,
    exampleProjectMembers1,
    exampleProfile1
)


val exampleProjectList = mutableListOf(exampleProject1,exampleProject2,exampleProject3,exampleProject4)

val exampleDataBase = DataBase(1,"학교","학교 정보 데이터베이스",1,0, exampleProject1)

val exampleDataBaseList = mutableListOf(exampleDataBase)

//테이블 예시
val exampleTable = Table(1,"학생 테이블","학생들의 정보를 모아둔 테이블", 1,0, exampleDataBase, LocalDateTime.now(),"a87e3cdf755500d7c6abf69813c444d72c2b2b664521e659f78ba17222ef44a0")
val exampleTable1 = Table(2,"교강사 테이블","교강사 정보를 모아둔 테이블", 1,0,exampleDataBase, LocalDateTime.now(),"a87e3cdf755500d7c6abf69813c444d72c2b2b664521e659f78ba17222ef44a1")
val exampleTable2 = Table(3,"삭제 테이블","삭제 정보를 모아둔 테이블", 0,1,exampleDataBase, LocalDateTime.now(),"a87e3cdf755500d7c6abf69813c444d72c2b2b664521e659f78ba17222ef44a2")
val exampleTable3 = Table(4,"과목 테이블","과목 정보를 모아둔 테이블", 0,0,exampleDataBase, LocalDateTime.now(),"a87e3cdf755500d7c6abf69813c444d72c2b2b664521e659f78ba17222ef44a3")

val exampleTableList = mutableListOf(exampleTable, exampleTable1, exampleTable2, exampleTable3)

//컬럼 예시
val exampleColumn1 = Column(1,"이름","학생 Table Primary Key","VarChar",1,0,0, exampleTable,null);
val exampleColumn2= Column(2,"나이","학생 Table 나이","Int",0,0,0,exampleTable,null);
val exampleColumn3= Column(3,"전화번호","학생 Table 전화번호","VarChar",0,0,0,exampleTable,null);
val exampleColumn4= Column(4,"주소","학생 Table 주소","VarChar",0,0,0,exampleTable,null);

val exampleColumnList = mutableListOf(exampleColumn1,exampleColumn2,exampleColumn3, exampleColumn4)

// 데이터 예시
val exampleData1 = Data(1, "어쩌구", 1, exampleColumn1)
val exampleData5 = Data(5, "김철수", 2,exampleColumn1)
val exampleData9 = Data(9, "이영희", 3,exampleColumn1)
val exampleData13 = Data(13, "박민수", 4,exampleColumn1)
val exampleData17 = Data(17, "윤석건", 5,exampleColumn1)


val exampleData2 = Data(2, "20", 1,exampleColumn2)
val exampleData6 = Data(6, "22", 2,exampleColumn2)
val exampleData10 = Data(10, "21", 3,exampleColumn2)
val exampleData14 = Data(14, "23", 4,exampleColumn2)
val exampleData18 = Data(18, "24", 5,exampleColumn2)

val exampleData3 = Data(3, "010-1234-5678", 1,exampleColumn3)
val exampleData7 = Data(7, "010-9876-5432", 2,exampleColumn3)
val exampleData11 = Data(11, "010-5555-4444", 4,exampleColumn3)
val exampleData15 = Data(15, "010-7777-8888", 3,exampleColumn3)
val exampleData19 = Data(18, "010-8502-3296", 5,exampleColumn3)

val exampleData4 = Data(4, "서울시 강남구", 1,exampleColumn4)
val exampleData8 = Data(8, "서울시 서초구", 2,exampleColumn4)
val exampleData12 = Data(12, "경기도 고양시", 3,exampleColumn4)
val exampleData16 = Data(16, "인천시 남구", 4,exampleColumn4)
val exampleData20 = Data(16, "인천시 남동구", 5,exampleColumn4)

val exampleDataList = mutableListOf(
    exampleData1, exampleData2, exampleData3, exampleData4,
    exampleData5, exampleData6, exampleData7, exampleData8,
    exampleData9, exampleData10, exampleData11, exampleData12,
    exampleData13, exampleData14, exampleData15, exampleData16,
    exampleData17, exampleData18, exampleData19, exampleData20
)