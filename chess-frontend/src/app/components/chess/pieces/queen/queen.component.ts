import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-queen',
  templateUrl: './queen.component.html',
  styleUrls: ['./queen.component.scss'],
})
export class QueenComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/bq.png'
      : 'assets/images/pieces/wq.png';
  }
}
