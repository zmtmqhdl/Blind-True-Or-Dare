package com.example.data.repository;

import com.example.data.room.RoomDao;
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
public final class RoomRepositoryImpl_Factory implements Factory<RoomRepositoryImpl> {
  private final Provider<RoomDao> daoProvider;

  public RoomRepositoryImpl_Factory(Provider<RoomDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public RoomRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static RoomRepositoryImpl_Factory create(javax.inject.Provider<RoomDao> daoProvider) {
    return new RoomRepositoryImpl_Factory(Providers.asDaggerProvider(daoProvider));
  }

  public static RoomRepositoryImpl_Factory create(Provider<RoomDao> daoProvider) {
    return new RoomRepositoryImpl_Factory(daoProvider);
  }

  public static RoomRepositoryImpl newInstance(RoomDao dao) {
    return new RoomRepositoryImpl(dao);
  }
}
