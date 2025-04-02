package com.example.myWorld.di;

import com.example.data.repository.RetrofitRepository;
import com.example.data.retrofit.RetrofitService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
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
public final class AppModule_ProvideRetrofitRepositoryFactory implements Factory<RetrofitRepository> {
  private final Provider<RetrofitService> retrofitServiceProvider;

  public AppModule_ProvideRetrofitRepositoryFactory(
      Provider<RetrofitService> retrofitServiceProvider) {
    this.retrofitServiceProvider = retrofitServiceProvider;
  }

  @Override
  public RetrofitRepository get() {
    return provideRetrofitRepository(retrofitServiceProvider.get());
  }

  public static AppModule_ProvideRetrofitRepositoryFactory create(
      javax.inject.Provider<RetrofitService> retrofitServiceProvider) {
    return new AppModule_ProvideRetrofitRepositoryFactory(Providers.asDaggerProvider(retrofitServiceProvider));
  }

  public static AppModule_ProvideRetrofitRepositoryFactory create(
      Provider<RetrofitService> retrofitServiceProvider) {
    return new AppModule_ProvideRetrofitRepositoryFactory(retrofitServiceProvider);
  }

  public static RetrofitRepository provideRetrofitRepository(RetrofitService retrofitService) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRetrofitRepository(retrofitService));
  }
}
