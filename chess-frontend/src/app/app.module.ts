import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginStatusComponent } from './components/login-status/login-status.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { SidePanelComponent } from './components/side-panel/side-panel.component';
import { BoardComponent } from './components/chess/board/board.component';
import { SquareComponent } from './components/chess/square/square.component';
import { KnightComponent } from './components/chess/pieces/knight/knight.component';
import { RookComponent } from './components/chess/pieces/rook/rook.component';
import { BishopComponent } from './components/chess/pieces/bishop/bishop.component';
import { QueenComponent } from './components/chess/pieces/queen/queen.component';
import { KingComponent } from './components/chess/pieces/king/king.component';
import { PawnComponent } from './components/chess/pieces/pawn/pawn.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    LoginStatusComponent,
    FooterComponent,
    HeaderComponent,
    SidePanelComponent,
    BoardComponent,
    SquareComponent,
    KnightComponent,
    RookComponent,
    BishopComponent,
    QueenComponent,
    KingComponent,
    PawnComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
