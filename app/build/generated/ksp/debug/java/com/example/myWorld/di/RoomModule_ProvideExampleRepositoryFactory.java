package com.example.myWorld.di;

import com.example.data.repository.RoomRepository;
import com.example.data.room.RoomDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RoomModule_ProvideExampleRepositoryFactory implements Factory<RoomRepository> {
  private final Provider<RoomDao> daoProvider;

  public RoomModule_ProvideExampleRepositoryFactory(Provider<RoomDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public RoomRepository get() {
    return provideExampleRepository(daoProvider.get());
  }

  public static RoomModule_ProvideExampleRepositoryFactory create(
      javax.inject.Provider<RoomDao> daoProvider) {
    return new RoomModule_ProvideExampleRepositoryFactory(Providers.asDaggerProvider(daoProvider));
  }

  public static RoomModule_ProvideExampleRepositoryFactory create(Provider<RoomDao> daoProvider) {
    return new RoomModule_ProvideExampleRepositoryFactory(daoProvider);
  }

  public static RoomRepository provideExampleRepository(RoomDao dao) {
    return Preconditions.checkNotNullFromProvides(RoomModule.INSTANCE.provideExampleRepository(dao));
  }
}
