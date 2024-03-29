# GPS

### https://school.programmers.co.kr/learn/courses/30/lessons/1837

### 문제 설명

카카오 택시 개발자 Jay-G는 다음 업데이트를 준비하기 위해 개선사항을 위한 여러 피드백을 받았다. 그중에서 손님이 자주 탑승하는 위치를 추천해주었으면 한다는 의견이 많았다.

다음 업데이트 준비를 위해 Jay-G는 택시의 승하차 및 이동 경로를 수집하여 분석하기 시작하였다. 데이터를 분석하던 Jay-G는 몇 가지 특이사항을 발견했다. 택시의 이동 경로를 GPS를 통해 수집하게 되는데, GPS 신호 불량, 통신 오류 등 다양한 원인으로 위치의 오류가 발생한 것을 알게 되었다. 다만 승차 위치와 하차 위치는 오류가 없는 것으로 확인이 되었다.

개발자 Jay-G는 수집한 이동 경로의 오류를 최소한으로 수정하여 좀 더 정확한 이동 경로를 구하고 싶어 한다.

택시는 다음과 같은 조건으로만 이동한다. 먼저 택시는 거점을 이동해 다니며, 거점 간의 이동은 해당하는 도로가 있는 경우에만 가능하다. 또한, 교통 상황에 따라 택시는 한 거점에 머무를 수 있고, 왔던 길을 되돌아갈 수 있다. 모든 도로는 방향이 별도로 없는 왕복 도로이다.

![Graph 1](https://t1.kakaocdn.net/codefestival/gps1.png)

예를 들어, 위 그래프에서 택시가 다음과 같이 시간대별로 이동 경로를 보내왔다.

| t   | 위치 |
| :-- | :--- |
| 1   | 1    |
| 2   | 2    |
| 3   | 3    |
| 4   | 3    |
| 5   | 6    |
| 6   | 7    |

하지만 위의 택시가 보내온 경로에는 `거점 3`에서 `거점 6`으로 이동할 수 있는 도로가 없으므로 이동 경로에 오류가 있다.

![Graph 2](https://t1.kakaocdn.net/codefestival/gps2.png)

이러한 오류를 최소한으로 수정하여 이동 가능한 경로로 만들고 싶다. 이 경우 1회의 오류를 수정하여 다음과 같이 이동 가능한 경로를 만들 수 있다. 시간 `t=4`의 위치를 `거점 5`로 한 번 수정하면 이동 가능한 경로가 된다.

| t   | 위치 |
| :-- | :--- |
| 1   | 1    |
| 2   | 2    |
| 3   | 3    |
| 4   | 5    |
| 5   | 6    |
| 6   | 7    |

![Graph 3](https://t1.kakaocdn.net/codefestival/gps3.png)

이와 비슷하게 시간 `t=4`의 위치를 `거점 4`로 바꾸거나, 시간 `t=5` 위치를 `거점 5`로 바꾸면 이동 가능한 경로로 만들 수 있다. 위의 경우 수정한 오류의 개수는 1개이다.

| t   | 위치 |
| :-- | :--- |
| 1   | 1    |
| 2   | 2    |
| 3   | 3    |
| 4   | 4    |
| 5   | 6    |
| 6   | 7    |

| t   | 위치 |
| :-- | :--- |
| 1   | 1    |
| 2   | 2    |
| 3   | 3    |
| 4   | 3    |
| 5   | 5    |
| 6   | 7    |

위와 같이 택시가 보내온 경로에서 이동 가능한 경로로 만드는 최소의 오류 수정 횟수를 구하여라.

### 입력 형식

주어지는 입력은 총 다섯 가지로, 거점 개수 `n`과 도로의 개수 `m`, 각 거점 간의 연결된 도로 정보 `edge_list`, 택시가 시간대별로 보내오는 거점 정보의 총 개수 `k`, 그리고 머물렀던 거점의 정보 `gps_log`이다. 제한조건은 아래와 같다.

-   2 <= `n` <= 200
-   1 <= `m` <= 10,000
-   2 <= `k` <= 100
-   `edge_list`는 `m × 2` 크기의 2차원 배열로, 각 행의 두 값은 도로가 잇는 두 거점의 번호를 의미한다.
-   거점의 번호는 1부터 n까지 숫자이다.
-   모든 도로는 양방향 통행이 가능하다.
-   입력되는 데이터에서 항상 모든 거점 간 경로가 있음이 보장되지 않는다.
-   `gps_log`의 시작 거점과 도착 거점은 바뀔 수 없다.

### 출력 형식

-   이동 가능한 경로로 만들 수 있는 최소의 오류 수정 횟수를 리턴한다. 올바른 경로로 수정하는 것이 불가능할 경우 `-1`을 리턴한다.

### 입출력 예

| n   | m    | edge_list                                                                          | k   | gps_log              | answer |
| :-- | :--- | :--------------------------------------------------------------------------------- | :-- | :------------------- | :----- |
| `7` | `10` | `[[1, 2], [1, 3], [2, 3], [2, 4], [3, 4], [3, 5], [4, 6], [5, 6], [5, 7], [6, 7]]` | `6` | `[1, 2, 3, 3, 6, 7]` | `1`    |
| `7` | `10` | `[[1, 2], [1, 3], [2, 3], [2, 4], [3, 4], [3, 5], [4, 6], [5, 6], [5, 7], [6, 7]]` | `6` | `[1, 2, 4, 6, 5, 7]` | `0`    |
