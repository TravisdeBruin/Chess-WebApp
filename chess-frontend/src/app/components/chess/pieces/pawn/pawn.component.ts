import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pawn',
  templateUrl: './pawn.component.html',
  styleUrls: ['./pawn.component.scss'],
})
export class PawnComponent {
  @Input()
  blackPiece: boolean = false;

  getImage() {
    return this.blackPiece
      ? 'assets/images/pieces/bp.png'
      : 'assets/images/pieces/wp.png';
  }
}
