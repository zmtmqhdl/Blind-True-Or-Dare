package com.example.presentation.viewModel;

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

  public HomeViewModel_Factory(Provider<RoomRepository> roomRepositoryProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(roomRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      javax.inject.Provider<RoomRepository> roomRepositoryProvider) {
    return new HomeViewModel_Factory(Providers.asDaggerProvider(roomRepositoryProvider));
  }

  public static HomeViewModel_Factory create(Provider<RoomRepository> roomRepositoryProvider) {
    return new HomeViewModel_Factory(roomRepositoryProvider);
  }

  public static HomeViewModel newInstance(RoomRepository roomRepository) {
    return new HomeViewModel(roomRepository);
  }
}
