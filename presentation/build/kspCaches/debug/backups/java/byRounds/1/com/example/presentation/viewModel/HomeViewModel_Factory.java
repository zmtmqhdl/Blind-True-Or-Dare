package com.example.presentation.viewModel;

import com.example.data.repository.RetrofitRepository;
import com.example.data.repository.RoomRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<RoomRepository> roomRepositoryProvider;

  private final Provider<RetrofitRepository> retrofitRepositoryProvider;

  public HomeViewModel_Factory(Provider<RoomRepository> roomRepositoryProvider,
      Provider<RetrofitRepository> retrofitRepositoryProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
    this.retrofitRepositoryProvider = retrofitRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(roomRepositoryProvider.get(), retrofitRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      javax.inject.Provider<RoomRepository> roomRepositoryProvider,
      javax.inject.Provider<RetrofitRepository> retrofitRepositoryProvider) {
    return new HomeViewModel_Factory(Providers.asDaggerProvider(roomRepositoryProvider), Providers.asDaggerProvider(retrofitRepositoryProvider));
  }

  public static HomeViewModel_Factory create(Provider<RoomRepository> roomRepositoryProvider,
      Provider<RetrofitRepository> retrofitRepositoryProvider) {
    return new HomeViewModel_Factory(roomRepositoryProvider, retrofitRepositoryProvider);
  }

  public static HomeViewModel newInstance(RoomRepository roomRepository,
      RetrofitRepository retrofitRepository) {
    return new HomeViewModel(roomRepository, retrofitRepository);
  }
}
