package com.example.datahub_back.service.treeService.commit

class AddChangesAsDataService {
}

//    [테이블끼리 비교해서 새로 추가된 행 데이터 찾기] - 기존 로직 참고
//    1. 이름이 같은 테이블끼리 찾아서 매핑
//    2. 그 테이블끼리 비교 반복문 시작
//    3. 해당 테이블의 행을 모두 찾아 리스트에 각각 넣음
//    4. 그 리스트로 반복문 시작
//    5. PK행인 것을 찾아서 객체를 리스트에 각각 넣음
//    6. 그 리스트로 비교 반복문 시작
//    7. 각각 데이터를 찾아서 리스트에 넣음
//    8. 차집합으로 새로 추가된 PK 데이터를 찾음
//    9. 그 PK 데이터의 columLine을 가지고 있고, 해당 column 객체를 가지고 있는 데이터를 모두 찾아 ChangeData에 데이터 저장
//    10. 그리고 역 추적으로  ChangeTable, ChangeColumn에 데이터 저장하되,
//    11. ChangeTable, ChangeColumn가 중복으로 저장 되어 있는지 확인 후 저장
