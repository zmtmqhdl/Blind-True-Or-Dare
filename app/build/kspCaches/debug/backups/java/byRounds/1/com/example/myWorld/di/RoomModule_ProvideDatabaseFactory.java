package com.example.myWorld.di;

import android.content.Context;
import com.example.data.room.RoomDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class RoomModule_ProvideDatabaseFactory implements Factory<RoomDatabase> {
  private final Provider<Context> contextProvider;

  public RoomModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RoomDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static RoomModule_ProvideDatabaseFactory create(
      javax.inject.Provider<Context> contextProvider) {
    return new RoomModule_ProvideDatabaseFactory(Providers.asDaggerProvider(contextProvider));
  }

  public static RoomModule_ProvideDatabaseFactory create(Provider<Context> contextProvider) {
    return new RoomModule_ProvideDatabaseFactory(contextProvider);
  }

  public static RoomDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(RoomModule.INSTANCE.provideDatabase(context));
  }
}
