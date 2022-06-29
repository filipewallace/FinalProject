import { UserService } from './services/user.service';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';

import { ModProfileComponent } from './components/modprofile/modprofile.component';
import { NgbModal, NgbModalConfig, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './services/auth.service';
import { CategoryService } from './services/category.service';
import { DeveloperService } from './services/developer.service';
import { GameService } from './services/game.service';
import { ModMediaService } from './services/mod-media.service';
import { PlatformService } from './services/platform.service';
import { PostService } from './services/post.service';
import { PublisherService } from './services/publisher.service';
import { RatingService } from './services/rating.service';
import { ReviewService } from './services/review.service';
import { LogoutComponent } from './components/logout/logout.component';
import { ModComponent } from './components/mod/mod.component';
import { GameComponent } from './components/game/game.component';
import { GameProfileComponent } from './components/game-profile/game-profile.component';
import { GameModsPipe } from './pipes/game-mods.pipe';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { ActiveUserPipe } from './pipes/active-user.pipe';
import { UserModsComponent } from './components/user-mods/user-mods.component';





@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ModProfileComponent,
    LogoutComponent,
    ModComponent,
    GameComponent,
    GameProfileComponent,
    GameModsPipe,
    UserManagementComponent,
    ActiveUserPipe,
    UserModsComponent





  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    BrowserModule,
    NgbModule

  ],

  providers: [
    UserService,
    AuthService,
    CategoryService,
    DeveloperService,
    GameService,
    ModMediaService,
    PlatformService,
    PostService,
    PublisherService,
    RatingService,
    ReviewService,
    NgbModalConfig,
    NgbModal

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
