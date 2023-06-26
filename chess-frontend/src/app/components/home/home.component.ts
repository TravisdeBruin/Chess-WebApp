import { Component, OnInit } from '@angular/core';
import { SocketService } from 'src/app/services/socket.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  ngOnInit(): void {}
  constructor(private socketService: SocketService) {}

  createGame() {
    this.socketService._connect();
  }
}

/**
 
  url: string = 'http://localhost:8080/';

  connectToSocket(gameId: number): void {
    const client = new Client({
      brokerURL: this.url + 'sow',
      onConnect: () => {
        client.subscribe('/topic/game-progress/' + gameId, (message) => {
          console.log(`Received: ${message.body}`);
          const data = JSON.parse(message.body);
          console.log(data);
        });
        client.publish({ destination: '/topic/test01', body: 'First Message' });
      },
    });

    client.activate();
  }

  createGame(): void {
    let name = 'johnsnow';
    if (name == null || name === '') {
      alert('Please enter name');
    } else {
      $.ajax({
        url: this.url + 'game/create',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
          name: name,
        }),
        success: function (data: any) {
          const gameId = data.id;
          // playerType = 'FIRST_PLAYER';
          // refreshGameBoard(data);
          this.connectToSocket(gameId);
          alert('Your created a game. Game id is: ' + data.id);
        },
        error: function (error: any) {
          console.log(error);
        },
      });
    }
  }
 */
