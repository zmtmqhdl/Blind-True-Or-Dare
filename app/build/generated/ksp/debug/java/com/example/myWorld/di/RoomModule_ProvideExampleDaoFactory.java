package com.example.myWorld.di;

import com.example.data.room.RoomDao;
import com.example.data.room.RoomDatabase;
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
public final class RoomModule_ProvideExampleDaoFactory implements Factory<RoomDao> {
  private final Provider<RoomDatabase> databaseProvider;

  public RoomModule_ProvideExampleDaoFactory(Provider<RoomDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomDao get() {
    return provideExampleDao(databaseProvider.get());
  }

  public static RoomModule_ProvideExampleDaoFactory create(
      javax.inject.Provider<RoomDatabase> databaseProvider) {
    return new RoomModule_ProvideExampleDaoFactory(Providers.asDaggerProvider(databaseProvider));
  }

  public static RoomModule_ProvideExampleDaoFactory create(
      Provider<RoomDatabase> databaseProvider) {
    return new RoomModule_ProvideExampleDaoFactory(databaseProvider);
  }

  public static RoomDao provideExampleDao(RoomDatabase database) {
    return Preconditions.checkNotNullFromProvides(RoomModule.INSTANCE.provideExampleDao(database));
  }
}
