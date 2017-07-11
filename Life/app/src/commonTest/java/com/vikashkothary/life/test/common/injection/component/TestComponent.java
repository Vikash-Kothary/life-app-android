package com.vikashkothary.life.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.vikashkothary.life.injection.component.ApplicationComponent;
import com.vikashkothary.life.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
