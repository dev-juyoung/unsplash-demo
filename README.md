## Unsplash Demo

### Environment

- OS: Max OS
- IDE: Android Studio 3.5.2

### Features

- 이미지 목록 페이지
  - RecyclerView를 통해 이미지의 목록을 표출한다.
- 이미지 상세 페이지
  - PhotoView를 통해 이미지의 Zoom in/out 기능을 제공한다.
  - BottomSheet를 통해 해당 이미지의 Exif 정보를 표출한다.

### Architecture

- MVVM Pattern
- Repository Pattern

### Usage

- Android Architecture Component
  - ViewModel
  - LiveData
  - DataBinding
- Networks
  - Retrofit 2
- 3rd Party Libraries
  - Glide
  - PhotoView
  - Timber