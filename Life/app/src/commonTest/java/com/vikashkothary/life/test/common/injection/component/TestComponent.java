package com.vikashkothary.life.test.common.injection.component;

import com.vikashkothary.life.injection.component.ApplicationComponent;
import com.vikashkothary.life.test.common.injection.module.ApplicationTestModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
