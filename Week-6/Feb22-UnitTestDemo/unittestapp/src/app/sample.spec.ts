import {compute} from './sample';

xdescribe('My First Test Suite', () => {

  beforeEach(() => {
    console.log("before each");
  })

  it('My First Test Spec', () => {
    expect(true).toBe(true);
  })

  it('Should return 0 if input is -ve', () => {
    const result = compute(-1);
    expect(result).toBe(0);
  })

  it('Should return input incremented by 1 if num is greater than 0', () => {
    const result = compute(2);
    expect(result).toBe(3);
  })

  it('Should concat the string', () => {
    let s1 = 'Hello';
    let s2 = ' World';
    const result = s1.concat(s2);
    expect(result).toEqual('Hello World');
  })
});
