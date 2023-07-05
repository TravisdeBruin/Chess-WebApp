import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  ngOnInit(): void {}
  constructor(
    private gameService: GameService,
    private storageService: StorageService
  ) {}
  isCreated = false;
  isCreatedFailed = false;
  errorMessage = '';

  createGame() {
    console.log('creating');
    const gameDto = {
      player: 'johnsnow',
      gameType: 'BULLET',
    };

    // Retrieve the token from sessionStorage
    const token = this.storageService.getToken();

    // Create the headers object with the Authorization header containing the token
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token);

    const response = this.gameService
      .createNewGame(gameDto, headers)
      .subscribe({
        next: (data) => {
          this.isCreated = false;
          this.isCreatedFailed = true;
        },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.isCreatedFailed = true;
        },
      });

    console.log(response);
  }
}
