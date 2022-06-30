import { ModProfileComponent } from './components/modprofile/modprofile.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { GameProfileComponent } from './components/game-profile/game-profile.component';
import { ModComponent } from './components/mod/mod.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { GameComponent } from './components/game/game.component';
import { UserModsComponent } from './components/user-mods/user-mods.component';
import { GameveiwComponent } from './components/gameveiw/gameveiw.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent },
  { path: 'mod', component: ModComponent },
  { path: 'games', component: GameComponent },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'logout', component: LogoutComponent },
  {path: 'users', component: UserManagementComponent },
  {path: 'mod/:id', component: ModProfileComponent},
  {path: 'user', component: UserModsComponent},
  {path: 'games/:id', component: GameveiwComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
