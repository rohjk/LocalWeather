# Local Weather

노정근

rohjk93@gmail.com

010 - 9949 - 7076



## 기술

Kotlin

Dagger-Hilt, Retrofit, Rx, Glide, JUnit, MockK

Repository Pattern, MVVM, DataBinding, Clean Architecture



## App 구조

Single Activity, Single Fragment

```
MainActivity : FragmentContainer

    - LocalWeatherFragment : 지역별 날씨 표시
```



## Package 구조

Layer 구분 - Domain, Data Layer를 구분하였습니다.

화면 구분 - 화면 구성 단위로 구분하고, 하위에 관련된 클래스를 위치시켰습니다.

이외 di, adapter 등 기능단위별로 구분하였습니다.



## Local Weahter Fragment 구조

```
SwipeRefreshLayout
|
|   RecyclerView
|   |
|   |   HeaderViewHolder
|   |   ItemViewHolder
|   |   ItemViewHolder
|   |   ItemViewHolder
|   |   ItemViewHolder
|   |
|   /RecyclerView
|
/SwipeRefreshLayout
```

SwipeRefreshLayout : 상단 스크롤 시, Refresh

RecyclerView : HeaderViewHolder, ItemViewHolder 두가지 View Type으로 화면을 구성합니다.

HeaderViewHolder : 테이블 상단 헤더를 구성합니다. TextView 3개로 구성되었습니다. (item_weather_list)

ItemViewHolder : 오늘, 내일 날씨를 표시합니다. TextView 및 WeatherInfoView로 구성되었습니다.(view_weather_info)



## WeatherInfoView

날씨 정보를 표시하는 Custom View 입니다.

리스트뷰에서 날씨 정보를 표시하는데 재사용하기위해 생성하였습니다.

ConstraintLayout을 사용하였고, databinding을 사용하였습니다.



## Dagger-Hilt DI

Hilt는 Component 생성이 필요없어 Dagger보다 사용이 쉬운 장점이 있습니다.

빠른 개발을 위해 Hilt를 적용하였습니다.



## UseCase

과제에서 요구되는 검색어가 포함된 지역의 날씨정보를 가져오기 위해서는 Metaweather의 두가지 Api를 사용해야합니다.

```
1. 검색어를 포함한 지역 목록 가져오기 (location/search)
2. 지역의 날씨 정보 가져오기 (ocation/{ID})
```

위 두가지 Api 별 UseCase를 작성하였습니다.

```
1. SearchLocationsUseCase - 검색어를 포함한 지역 목록 가져오기
2. GetLocationWeatherUseCase - 지역의 날씨 정보 가져오기
```

최종적으로 과제에서 요구되는 검색어가 포함된 지역의 날씨를 가져오는 SearchLocationWeathersUseCase를 생성하였고, 이것은 상위 두가지 UseCase를 조합하여 사용합니다.

```
SearchLocationWeathersUseCase - 검색어를 포함한 지역 목록 가져온 후(SearchLocationsUseCase), 지역의 날씨 정보 가져오기(GetLocationWeatherUseCase)
```

날씨 목록에서 특정 지역을 선택하여 디테일한 정보를 표시하는 기능을 구현할 때 SearchLocationsUseCase, GetLocationWeatherUseCase를 사용하여 쉽게 기능구현 할 수 있습니다.



## Rx

비즈니스 로직에 Rx를 사용하였습니다.

과제 데모 동영상에서 날씨정보를 Refesh 해도 목록의 순서는 바뀌지 않는 것을 확인하였습니다.

지역 검색 후, 서버로부터 받은 목록 순서 그대로 표시되는 것을 확인할 수 있었습니다.

각 지역의 날씨 정보를 가져오는 단계에서 순서를 보장하기 위하여 concatMapEager 연산사를 사용하였습니다.

concatMapEager 연산자는 동시성, 순서를 보장합니다.



## DataBinding

모든 Layout은 DataBindingd으로 데이터와 연결되어 화면을 구성하고 업데이트 합니다.



## Unit Test

Mapper, DataSource, Repository, ViewModel Unit Test를 작성하였습니다.

JUnit + MockK 라이브러리를 사용했습니다.



## Dark Theme

![initial](https://github.com/JakeRoh/LocalWeather/blob/master/image/localweather_light.png)

Light Theme

![initial](https://github.com/JakeRoh/LocalWeather/blob/master/image/localweather_dark.png)

Dark Theme

Dark Theme를 고려하여 Color를 ligh, night로 구분하여 작성하였습니다.
