# 산 모양 타일링

### https://school.programmers.co.kr/learn/courses/30/lessons/258705

### 문제 설명

한 변의 길이가 1인 정삼각형 `2n+1`개를 이어붙여 윗변의 길이가 `n`, 아랫변의 길이가 `n+1`인 사다리꼴을 만들 수 있습니다. 이때 사다리꼴의 윗변과 변을 공유하는 `n`개의 정삼각형 중 일부의 위쪽에 같은 크기의 정삼각형을 붙여 새로운 모양을 만들었습니다. 예를 들어 `n`이 4이고, 1번째, 2번째, 4번째 정삼각형 위에 정삼각형을 붙인 모양은 다음과 같습니다.

![pic1.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/b1eb2bdf-c4a8-4750-8a0d-1fe1a304ccaf/pic1.png)

이렇게 만든 모양을 **`정삼각형 타일`** 또는 정삼각형 2개를 이어 붙인 <strong>`마름모 타일`</strong>로 빈 곳이 없도록 채우려고 합니다. 정삼각형 타일과 마름모 타일은 돌려서 사용할 수 있습니다.

![pic2.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/f145dc29-ce8b-4077-ad71-9442b1c4434f/pic2.png)

타일을 놓을 때 다른 타일과 겹치거나 모양을 벗어나게 놓을 수는 없습니다. 위의 예시 모양을 채우는 방법 중 일부는 다음과 같습니다.

![pic3.png](https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/89dbb27a-f939-4b71-abd9-3de304f67c4e/pic3.png)

사다리꼴의 윗변의 길이를 나타내는 정수 `n`과 사다리꼴 윗변에 붙인 정삼각형을 나타내는 1차원 정수 배열 `tops`가 매개변수로 주어집니다. 이때 문제 설명에 따라 만든 모양을 정삼각형 또는 마름모 타일로 빈 곳이 없도록 채우는 경우의 수를 `10007`로 나눈 나머지를 return 하도록 solution 함수를 완성해 주세요.

### 제한 조건

-   1 ≤ `n` ≤ 100,000
-   `tops`의 길이 = `n`
    -   `tops[i]`는 사다리꼴의 윗변과 변을 공유하는 `i+1`번째 정삼각형의 위쪽에 정삼각형을 붙이는 경우 1, 붙이지 않는 경우 0입니다.

### 입출력 예

| n    | tops                             | result |
| :--- | :------------------------------- | :----- |
| `4`  | `[1, 1, 0, 1]`                   | `149`  |
| `2`  | `[0, 1]`                         | `11`   |
| `10` | `[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]` | `7704` |
