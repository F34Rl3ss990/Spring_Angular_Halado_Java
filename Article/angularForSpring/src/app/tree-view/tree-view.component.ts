import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from "../user.service";
import {Tree} from "../../models/tree";
import { JsonEditorComponent, JsonEditorOptions } from 'ang-jsoneditor';


@Component({
  selector: 'app-tree-view',
  template: '<json-editor [options]="editorOptions" [data]="tree_data"></json-editor>',
  styleUrls: ['./tree-view.component.css']
})
export class TreeViewComponent implements OnInit {
  public tree_data: Tree[];

  public editorOptions: JsonEditorOptions;


  @ViewChild(JsonEditorComponent, { static: false }) editor : JsonEditorComponent;

  constructor( private us: UserService) {
    this.editorOptions = new JsonEditorOptions();
    this.editorOptions.modes = ['code', 'text', 'tree', 'view'];
  }

  ngOnInit(): void {
    this.us.getUsersList().subscribe((data: Tree[]) =>{
      this.tree_data = data;
      console.log(data)
    });
  }


}
