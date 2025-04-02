package com.example.myWorld.di;

import com.example.data.retrofit.RetrofitService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideRetrofitServiceFactory implements Factory<RetrofitService> {
  @Override
  public RetrofitService get() {
    return provideRetrofitService();
  }

  public static AppModule_ProvideRetrofitServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RetrofitService provideRetrofitService() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRetrofitService());
  }

  private static final class InstanceHolder {
    static final AppModule_ProvideRetrofitServiceFactory INSTANCE = new AppModule_ProvideRetrofitServiceFactory();
  }
}
