## DiffUtil

Android에서 Recycler view를 사용할 때 아이템의 목록을 최신화 시키기 위해서 `notifyDataSetChanged()`라는 메서드를 이용해 왔다. 하지만 이 메서드는 비용이 상당히 많이 드는 메서드이다. Adapter에게 Recycler view의 아이템이 변경되었으니 리스트를 모두 지우고 다시 처음부터 끝까지 객체를 다시 만들어 렌더링하라는 명령이기 때문이다. 아이템의 개수가 많아지게 된다면 UX에도 분명히 악영향을 끼치게 될 것이다. 따라서, 이를 해결하기 위한 방법이 필요하다.



### 정의

`notifyDataSetChanged()` 메서드의 비용 문제를 해결하기 위해 등장한 것이 **`DiffUtil`** 클래스이다. Recycler view 라이브러리에 포함되어 있다.

DiffUtil은 이전 데이터의 상태와 현재 데이터 간의 차이를 계산하며, 반드시 업데이트 해야 할 최소한의 데이터에 대해서만 갱신하게 된다. 데이터 업데이트 횟수를 최소한으로 가져가기 위함이다. Eugene W. Myers의 difference algorithms가 사용됐다고 한다.



### 사용법

우선 DiffUtil이 두 리스트의 차이를 계산하기 위해서는 `DiffUtil.Callback`을 구현해야 한다. 해당 콜백에는 4개의 추상 메서드와 1개의 메서드가 포함되어 있다. 다음 4개의 메서드를 오버라이딩하여 사용한다.

- **areContentsTheSame()** : 같다고 알려진 두 아이템의 변경 유무를 확인한다. false인 경우 변경이 일어난 것이다.
- **areItemsTheSame()** : 두 아이템이 동일한 아이템인지 체크한다. item이 고유한 id를 갖고 있다면, 그것으로 설정하면 된다.
- **getOldListSize()** : 이전 리스트의 크기를 반환한다.
- **getNewListSize()** : 새로운 리스트의 크기를 반환한다.



다만, 아이템의 개수가 많아지게 되는 경우 비교 연산 시간이 크게 늘어날 수 있으므로 비교 연산은 백그라운드 스레드에서 처리해야 한다. 따라서, 개발자는 직접 백그라운드 스레드에서 비교 처리를 수행하고 메인 스레드에서 결과를 처리하는 코드를 작성해야 한다. `AsyncListDiffer` 클래스는 이러한 과정을 매우 쉽게 접근하고 구현할 수 있도록 도와 준다.

`AsyncListDiffer`는 DiffUtil보다 훨씬 사용법이 간단하다. 우선, `DiffUtil.ItemCallback`을 구현한다. 여기에는 2개의 추상 메서드가 존재한다.

- **areItemsTheSame()**
- **areContentsTheSame()**



`AsyncListDiffer`를 더 쓰기 쉽게 래핑한 클래스가 `ListAdapter`이다.


### References

[[Android] DiffUtil 사용법 알아보기](https://velog.io/@haero_kim/Android-DiffUtil-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)

[Android Docs - DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)

[[Android] ListAdapter로 RecyclerView 효율적으로 사용하기 (DiffUtil, AsyncListDiffer)](https://zion830.tistory.com/86)