import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-king',
  templateUrl: './king.component.html',
  styleUrls: ['./king.component.scss'],
})
export class KingComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/bk.png'
      : 'assets/images/pieces/wk.png';
  }
}
