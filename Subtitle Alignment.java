/* Important constants used in our program */
static final int number_of_APIs = a; // number of speech-to-text APIs available in our system
static int audio_samples_millisecond; // usually found in an audio file's header information
static final double size_frame = 30.0; // usual size of an audio frame
static number_frames; // this is the number of virtual audio frames composing the audio stream
static int used_API = Random.nextInt(number); // random selection of an API
static double[] sound_energy; // array of doubles that will store the audio energy of each frame
static double silence_threshold_percentage = b; // used silence_threshold_percentage
static double silence_threshold; // silence threshold for this video
static final int min_speech_segment = c; // minimum number of frames needed for word utterance
static final int min_distanceTo_silence = d; // minimum number of frames needed between silent frames
static final int consecutive_silence_threshold = e; // minimum number of consecutive silence frames
static final int offset = (int)(consecutive_silence_threshold / 2);

/* Function Declarations. These are all the functions used in the main body of our program. */

// Function that initializes all uninitilaized variables above and
// returns audio file of the stream for use with the speech-to-text API
Audio Initializer(String VideoFileName)
{
   Audio file = // Extract audio from video

   int length = // obtain the duration, in milliseconds, of audio file from file's header info
   int last_frame = length % size_frame; // obtain the size of the last frame
   int audio_samples_millisecond = // obtain this number from audio file's header info
   int number_frames = Math.ceil(length / size_frame); // obtain number of audio frames
   
   // initialize sound energy array and calculate energy for each frame
   sound_energy = new double[number_frames];
   for(int i = 0; i < number_frames - 1; i += 1)
   {
      // sum the energy level of all the audio samples in this audio frame
      double sum = 0;
      int N = audio_samples_millisecond * 30;
      for(j = 0; j < N; j += 1)
      {
         sum += // add sound energy of this audio sample
      } 
      
      // take the square root of this sum and divide it by N. This  
      // will be the array entry in the sound_energy array
      sound_energy[i] = Math.sqrt(sum) / N;
   }
   // calculate energy level of last frame
   double sum = 0;
   int N = audio_samples_millisecond * last_frame;
   for (int i = 0; i < N; i += 1)
   {
      sum += // add sound energy of this audio sample
   }
   // take the square root of this sum and divide it by N. This  
   // will be the array entry in the sound_energy array
   sound_energy[number_frames - 1] = Math.sqrt(sum) / N;

   // calculate silence_threshold
   double[] temp = double[number_frames];
   for (int i = 0; i < number_frames; i += 1)
   {
      temp[i] = sound_energy[i];
   }
   temp.sort();
   silence_threshold = temp[(int)silence_threshold_percentage * number_frames];

   // finally, return audio file of the video's audio stream
   return file;
}

// Functions for API's. Return transcribed text in form of String
String API0(Audio chunk)
{
   // String variable that will store transcription result
   String result;

   // Utilize the API to transcribe this chunk...

   return result;
}
String API1(Audio chunk)
{
   // String variable that will store transcription result
   String result;

   // Utilize the API to transcribe this chunk...

   return result;
}
String API2(Audio chunk)
{
   // String variable that will store transcription result
   String result;

   // Utilize the API to transcribe this chunk...

   return result;
}

// Function that utilizes the randomly chosen API at the beginning to transcribe the audio chunk
String SpeechToText(Audio chunk)
{
   // switch statement for producing the text using whatever API 
   // was randomly chosen at the beginning of the program
   switch(used_API)
   {
      case 0:
         return API0(chunk);
      case 1:
         return API1(chunk);
      case 2:
         return API2(chunk);
   }
}

// Function that provides the .srt file for given mp3 argument. 
// Return true for success and false for failure
boolean SubtitleGenerator(String AudioFileName)
{
   // Counter for speech segment (used in .srt file)
   int id = 1;

   // Make new file and append to it ".srt" extension
   PrintWriter writer = new PrintWriter(AudioFileName + ".srt", "UTF-8");

   /* go through sound energy array looking for speech segments */
   int i = 0;
   int last_silence = 0;
   int num_silent_frames = 0;
   while (i < number_frames)
   {
      // mark the beginning and ending of speech
      int beginning = i;
      int end = i;
      
      // As long as there is possible speech...
      while (num_silent_frames <= consecutive_silence_threshold)
      {
         if (sound_energy[i] <= silence_threshold)
         {
            num_silent_frames += 1;
         }
         else
         {
            num_silent_frames = 0;
         }
         end += 1;
         i += 1;
      }

      // mark the end of this silence sequence
      int this_silence = i;

      // If there was possible speech at least length of min_speech_segment...
      if ((end - index) > min_speech_segment && (last_silence - this_silence) > min_distanceTo_silence)
      {
         last_silence = this_silence; // reset last_silence

         Audio chunk = // obtain chunk of audio file correspoding to interval

         String result = SpeechToText(chunk); // obtain transcription of chunk

         /*** write that segment of speech to .srt file ***/
         
         // first write id of the speech segment
         writer.println(id);

         // convert from frames to milliseconds
         beginning *= size_frame;
         end *= size_frame;

         // calculate start time of this speech segment
         int hours1 = beginning / (60 * 60 * 1000);
         int minutes1 = (beginning / (60 * 1000)) % 60;
         int seconds1 = (beginning / 1000) % 60;
         int millseconds1 = beginning % 1000;
         
         // calculate end time of this speech segement 
         end -= offset;
         int hours2 = end / (60 * 60 * 1000);
         int minutes2 = (end / (60 * 1000)) % 60;
         int seconds2 = (end / 1000) % 60;
         int millseconds2 = end % 1000;

         // write start and end times to .srt file
         writer.println(String.format("%02d", hours1)
            + ":" + String.format("%02d", minutes1) 
            + ":" + String.format("%02d", seconds1)
            + "," + String.format("%03d", milliseconds1)
            + " --> " + String.format("%02d", hours21)
            + ":" + String.format("%02d", minutes2) 
            + ":" + String.format("%02d", seconds2)
            + "," + String.format("%03d", milliseconds2));

         // write the transcription
         writer.println(string);
         writer.println();

         id += 1; // increase id for next subtitle

         del(chunk); // delete the temporary chunk Audio file
      }
   }

   // close writer (SRT) file
   writer.close();
   
   del(AudioFileName); // delete temporary mp3 file of audio stream

   return true;
}