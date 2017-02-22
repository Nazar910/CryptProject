'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

const languages = {
              en:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz",
              ru:"АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя",
              ua:"АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя"
            };

const App = React.createClass({
    getInitialState() {
        return {
            message: "",
            key: 1,
            cryptOption: "encrypt",
            cryptType: "caesar",
            lang: languages["en"],
            brute_force:false,
            message_array:[],
            intelliSearch:false
        }  
    },

    onKeyChange(event) {
        if (!!event.target.value.match(/^\d*$/)) {
            this.setState({ key: event.target.value });
        }
    },

    onMessageChange(event) {
        let str = event.target.value;
        let flag = true;
        for(let i = 0; i < str.length; i++) {
            if (!this.state.lang.includes(str.charAt(i))) {
                flag = false;
                break;
            }
        }
        if (flag) {
            this.setState({ message: event.target.value });  
        }
    },

    encryptRequest() {
        axios.post('/api/crypt/' + this.state.cryptType + '/encrypt',
         {message:this.state.message, key:this.state.key, alphabet: this.state.lang, isIntelliSearch: this.state.intelliSearch})
          .then((response)=>{
            return response.data;
        })
          .then((data)=>{ this.setState({message: data}); });
    },

    decryptRequest() {
        axios.post('/api/crypt/'+ this.state.cryptType + '/decrypt', 
          {message:this.state.message, key:this.state.key, alphabet: this.state.lang, isIntelliSearch: this.state.intelliSearch})
          .then((response)=>{
            return response.data;
        })
          .then((data)=>{ 
            if (data.constructor === Array) {
              this.setState({ message_array: data });
            } else {
              this.setState({ message: data });
            }
          });
    },

    onEncryptButton() {
      this.setState({ 
        cryptOption: "encrypt",
        intelliSearch: false,
        brute_force: false
      });
      document.getElementById("key").disabled = false;
    },

    onDecryptButton() {
      this.setState({ 
        cryptOption: "decrypt",
        intelliSearch: false,
        brute_force: false
      });
      document.getElementById("key").disabled = false;
    },

    onCryptTypeChange(event) {
      this.setState({ cryptType: event.target.value.toLowerCase() });
    },

    onLangChange(event) {
      this.setState({ lang: languages[event.target.value] });
    },

    onBruteForce() {
      this.setState({key: 0, brute_force: !this.state.brute_force, cryptOption: "decrypt"});
       let flag = document.getElementById("key").disabled;
       document.getElementById("key").disabled = !flag;

    },

    onBruteForceItemClick(newMessage) {
      this.setState({ message: newMessage, message_array: [], key: 1 });
      document.getElementById("close").click();
    },

    onIntelliSearchClick() {
      this.setState({ intelliSearch: true });
    },

    render() {
        return (
            <div>
                <div className="container">
                  <div className="header clearfix">
                    <nav>
                      <ul className="nav nav-pills pull-right">
                        <li role="presentation"><a href="#" onClick={this.onEncryptButton}>Encrypt</a></li>
                        <li role="presentation"><a href="#" onClick={this.onDecryptButton}>Decrypt</a></li>
                        <li role="presentation"><a href="#" onClick={this.onBruteForce}>Brute force</a></li>
                      </ul>
                    </nav>
                  </div>

                  <div className="jumbotron">
                    <textarea 
                    className="form-control" value={this.state.message}
                    rows="5" id="comment" onChange={this.onMessageChange}>
                      
                    </textarea>
                    <div className="form-group" id="select">
                      {this.state.brute_force ? 
                          <input 
                            type="checkbox" 
                            id="search"
                            title="intelliSearch"
                            onClick={this.onIntelliSearchClick}
                            value="i"
                          />
                          :""}
                      <input 
                        type="text" className="form-control" 
                        id="key" onChange={this.onKeyChange}
                        value={this.state.key}
                      /><br/><br/>
                      <select className="form-control" id="cipher" onChange={this.onCryptTypeChange}>
                        <option>Caesar</option>
                      </select>
                      <br/><br/>
                      <select className="form-control" id="lang" onChange={this.onLangChange}>
                        <option>en</option>
                        <option>ua</option>
                        <option>ru</option>
                      </select>
                    </div>
                    <div id="crypt">
                      { this.state.cryptOption === "encrypt" ?
                          <button 
                          className="btn btn-lg btn-success" 
                          onClick={this.encryptRequest}>
                            Encrypt
                          </button>
                          :
                          !this.state.brute_force ?
                          <button 
                          className="btn btn-lg btn-success" 
                          onClick={this.decryptRequest}>
                            Decrypt
                          </button>
                           :
                          <button
                         type="button" 
                         className="btn btn-lg btn-success" 
                         data-toggle="modal" 
                         data-target="#myModal"
                         onClick={this.decryptRequest}>
                          Decrypt
                        </button>
                      }


                        <div id="myModal" className="modal fade" role="dialog">
                          <div className="modal-dialog">

                            <div className="modal-content">
                              <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal">&times;</button>
                                <h4 className="modal-title">Modal Header</h4>
                              </div>
                              <div className="modal-body">
                                {this.state.message_array.map(message=>
                                  <p 
                                    key={message} 
                                    onClick={this.onBruteForceItemClick.bind(this,message)}
                                    >
                                      {message}
                                  </p>)}
                              </div>
                              <div className="modal-footer">
                                <button id="close" type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                              </div>
                            </div>

                          </div>
                        </div>
                    </div>
                  </div>

            </div>
            <footer className="footer">
             <p>&copy; 2017 Nazarii Pyvovar, TR-41</p>
            </footer>
        </div>
        )
    }
});

export default App;

/*{ this.state.cryptOption === "decrypt" ? 
                        <span>
                          <input 
                            type="checkbox" 
                            id="brute-force" 
                            title="brute-force"
                            onClick={this.onBruteForce}
                          />
                          <input 
                            type="checkbox" 
                            id="intelli-search" 
                            title="intelliSearch"
                          />
                        </span>
                          : ""}*/